package solsolsol.mukpick.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solsolsol.mukpick.domain.auth.dto.AuthResponse;
import solsolsol.mukpick.domain.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 카카오 로그인 콜백
     * 프론트에서 카카오 인가 코드를 받아 서버로 전달
     * GET /api/auth/kakao/callback?code=인가코드
     */
    @GetMapping("/kakao/callback")
    public ResponseEntity<AuthResponse> kakaoCallback(@RequestParam String code) {
        return ResponseEntity.ok(authService.kakaoLogin(code));
    }
}
