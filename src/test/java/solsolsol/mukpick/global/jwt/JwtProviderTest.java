package solsolsol.mukpick.global.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import solsolsol.mukpick.global.config.JwtProperties;

import static org.assertj.core.api.Assertions.assertThat;

class JwtProviderTest {

    private JwtProvider jwtProvider;

    @BeforeEach
    void setUp() {
        JwtProperties properties = new JwtProperties();
        properties.setSecret("test-secret-key-must-be-at-least-32-characters!!");
        properties.setAccessTokenExpiration(15);
        properties.setRefreshTokenExpiration(10080);

        jwtProvider = new JwtProvider(properties);
    }

    @Test
    @DisplayName("Access Token 생성 - userId로 토큰을 생성하고 파싱할 수 있다")
    void generateAccessToken_success() {
        String token = jwtProvider.generateAccessToken(1L);

        assertThat(token).isNotBlank();
        assertThat(jwtProvider.getUserId(token)).isEqualTo(1L);
    }

    @Test
    @DisplayName("Refresh Token 생성 - userId로 토큰을 생성하고 파싱할 수 있다")
    void generateRefreshToken_success() {
        String token = jwtProvider.generateRefreshToken(1L);

        assertThat(token).isNotBlank();
        assertThat(jwtProvider.getUserId(token)).isEqualTo(1L);
    }

    @Test
    @DisplayName("토큰 검증 - 유효한 토큰은 true를 반환한다")
    void validate_validToken() {
        String token = jwtProvider.generateAccessToken(1L);

        assertThat(jwtProvider.validate(token)).isTrue();
    }

    @Test
    @DisplayName("토큰 검증 - 잘못된 토큰은 false를 반환한다")
    void validate_invalidToken() {
        assertThat(jwtProvider.validate("invalid.token.value")).isFalse();
    }

    @Test
    @DisplayName("토큰 검증 - 빈 문자열은 false를 반환한다")
    void validate_emptyToken() {
        assertThat(jwtProvider.validate("")).isFalse();
    }
}
