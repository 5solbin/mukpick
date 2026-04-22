package solsolsol.mukpick.domain.auth.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import solsolsol.mukpick.domain.auth.dto.KakaoTokenResponse;
import solsolsol.mukpick.domain.auth.dto.KakaoUserInfo;
import solsolsol.mukpick.global.config.KakaoProperties;

@Component
@RequiredArgsConstructor
public class KakaoClient {

    private final KakaoProperties kakaoProperties;
    private final RestTemplate restTemplate;

    private static final String TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    // 인가 코드로 카카오 액세스 토큰 발급
    public KakaoTokenResponse getToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoProperties.getClientId());
        body.add("redirect_uri", kakaoProperties.getRedirectUri());
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                TOKEN_URL, HttpMethod.POST, request, KakaoTokenResponse.class);

        return response.getBody();
    }

    // 카카오 액세스 토큰으로 유저 정보 조회
    public KakaoUserInfo getUserInfo(String kakaoAccessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(kakaoAccessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<KakaoUserInfo> response = restTemplate.exchange(
                USER_INFO_URL, HttpMethod.GET, request, KakaoUserInfo.class);

        return response.getBody();
    }
}
