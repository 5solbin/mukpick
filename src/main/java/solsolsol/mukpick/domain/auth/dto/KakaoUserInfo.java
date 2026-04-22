package solsolsol.mukpick.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoUserInfo {

    private String id;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    public static class KakaoAccount {

        private String email;
        private Profile profile;

        @Getter
        public static class Profile {
            private String nickname;

            @JsonProperty("profile_image_url")
            private String profileImageUrl;
        }
    }

    public String getEmail() {
        return kakaoAccount.getEmail();
    }

    public String getNickname() {
        return kakaoAccount.getProfile().getNickname();
    }

    public String getProfileImageUrl() {
        return kakaoAccount.getProfile().getProfileImageUrl();
    }
}
