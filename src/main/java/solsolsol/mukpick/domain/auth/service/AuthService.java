package solsolsol.mukpick.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solsolsol.mukpick.domain.auth.client.KakaoClient;
import solsolsol.mukpick.domain.auth.dto.AuthResponse;
import solsolsol.mukpick.domain.auth.dto.KakaoTokenResponse;
import solsolsol.mukpick.domain.auth.dto.KakaoUserInfo;
import solsolsol.mukpick.domain.user.entity.User;
import solsolsol.mukpick.domain.user.repository.UserRepository;
import solsolsol.mukpick.global.jwt.JwtProvider;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final KakaoClient kakaoClient;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public AuthResponse kakaoLogin(String code) {
        // 1. 인가 코드로 카카오 액세스 토큰 발급
        KakaoTokenResponse kakaoToken = kakaoClient.getToken(code);

        // 2. 카카오 액세스 토큰으로 유저 정보 조회
        KakaoUserInfo userInfo = kakaoClient.getUserInfo(kakaoToken.getAccessToken());

        // 3. 신규 유저면 회원가입, 기존 유저면 그대로 조회 (회원가입/로그인 통합)
        User user = userRepository.findByKakaoId(userInfo.getId())
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .kakaoId(userInfo.getId())
                                .email(userInfo.getEmail())
                                .nickname(userInfo.getNickname())
                                .profileImageUrl(userInfo.getProfileImageUrl())
                                .role(User.Role.USER)
                                .build()
                ));

        // 4. JWT 발급
        return issueTokens(user);
    }

    @Transactional(readOnly = true)
    public AuthResponse reissueToken(String refreshToken) {
        // 1. refresh token 유효성 검증
        if (!jwtProvider.validate(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 refresh token입니다.");
        }

        // 2. refresh token에서 userId 추출 후 유저 조회
        Long userId = jwtProvider.getUserId(refreshToken);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        // 3. 새 토큰 발급
        return issueTokens(user);
    }

    private AuthResponse issueTokens(User user) {
        String accessToken = jwtProvider.generateAccessToken(user.getId());
        String refreshToken = jwtProvider.generateRefreshToken(user.getId());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}
