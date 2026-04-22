package solsolsol.mukpick.domain.auth.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import solsolsol.mukpick.domain.auth.client.KakaoClient;
import solsolsol.mukpick.domain.auth.dto.AuthResponse;
import solsolsol.mukpick.domain.auth.dto.KakaoTokenResponse;
import solsolsol.mukpick.domain.auth.dto.KakaoUserInfo;
import solsolsol.mukpick.domain.user.entity.User;
import solsolsol.mukpick.domain.user.repository.UserRepository;
import solsolsol.mukpick.global.jwt.JwtProvider;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private KakaoClient kakaoClient;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private KakaoTokenResponse kakaoTokenResponse;

    @Mock
    private KakaoUserInfo kakaoUserInfo;

    private User buildUser() {
        return User.builder()
                .kakaoId("kakao-123")
                .email("test@kakao.com")
                .nickname("테스터")
                .profileImageUrl("https://example.com/profile.jpg")
                .role(User.Role.USER)
                .build();
    }

    @Test
    @DisplayName("카카오 로그인 - 신규 유저면 회원가입 후 토큰을 반환한다")
    void kakaoLogin_newUser() {
        given(kakaoClient.getToken("code")).willReturn(kakaoTokenResponse);
        given(kakaoTokenResponse.getAccessToken()).willReturn("kakao-access-token");
        given(kakaoClient.getUserInfo("kakao-access-token")).willReturn(kakaoUserInfo);
        given(kakaoUserInfo.getId()).willReturn("kakao-123");
        given(kakaoUserInfo.getEmail()).willReturn("test@kakao.com");
        given(kakaoUserInfo.getNickname()).willReturn("테스터");
        given(kakaoUserInfo.getProfileImageUrl()).willReturn("https://example.com/profile.jpg");

        User newUser = buildUser();
        given(userRepository.findByKakaoId("kakao-123")).willReturn(Optional.empty());
        given(userRepository.save(any(User.class))).willReturn(newUser);
        given(jwtProvider.generateAccessToken(any())).willReturn("access-token");
        given(jwtProvider.generateRefreshToken(any())).willReturn("refresh-token");

        AuthResponse response = authService.kakaoLogin("code");

        assertThat(response.getAccessToken()).isEqualTo("access-token");
        assertThat(response.getRefreshToken()).isEqualTo("refresh-token");
        assertThat(response.getNickname()).isEqualTo("테스터");
        verify(userRepository).save(any(User.class)); // 저장 호출 확인
    }

    @Test
    @DisplayName("카카오 로그인 - 기존 유저면 저장 없이 토큰을 반환한다")
    void kakaoLogin_existingUser() {
        given(kakaoClient.getToken("code")).willReturn(kakaoTokenResponse);
        given(kakaoTokenResponse.getAccessToken()).willReturn("kakao-access-token");
        given(kakaoClient.getUserInfo("kakao-access-token")).willReturn(kakaoUserInfo);
        given(kakaoUserInfo.getId()).willReturn("kakao-123");

        User existingUser = buildUser();
        given(userRepository.findByKakaoId("kakao-123")).willReturn(Optional.of(existingUser));
        given(jwtProvider.generateAccessToken(any())).willReturn("access-token");
        given(jwtProvider.generateRefreshToken(any())).willReturn("refresh-token");

        AuthResponse response = authService.kakaoLogin("code");

        assertThat(response.getAccessToken()).isEqualTo("access-token");
        verify(userRepository, never()).save(any()); // 저장 미호출 확인
    }

    @Test
    @DisplayName("토큰 재발급 - 유효한 refresh token이면 새 토큰을 반환한다")
    void reissueToken_success() {
        User user = buildUser();
        given(jwtProvider.validate("valid-refresh-token")).willReturn(true);
        given(jwtProvider.getUserId("valid-refresh-token")).willReturn(1L);
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(jwtProvider.generateAccessToken(any())).willReturn("new-access-token");
        given(jwtProvider.generateRefreshToken(any())).willReturn("new-refresh-token");

        AuthResponse response = authService.reissueToken("valid-refresh-token");

        assertThat(response.getAccessToken()).isEqualTo("new-access-token");
        assertThat(response.getRefreshToken()).isEqualTo("new-refresh-token");
    }

    @Test
    @DisplayName("토큰 재발급 - 유효하지 않은 refresh token이면 예외를 던진다")
    void reissueToken_invalidToken() {
        given(jwtProvider.validate("invalid-token")).willReturn(false);

        assertThatThrownBy(() -> authService.reissueToken("invalid-token"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 refresh token입니다.");
    }

    @Test
    @DisplayName("토큰 재발급 - 존재하지 않는 유저면 예외를 던진다")
    void reissueToken_userNotFound() {
        given(jwtProvider.validate("valid-refresh-token")).willReturn(true);
        given(jwtProvider.getUserId("valid-refresh-token")).willReturn(999L);
        given(userRepository.findById(999L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> authService.reissueToken("valid-refresh-token"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 유저입니다.");
    }
}
