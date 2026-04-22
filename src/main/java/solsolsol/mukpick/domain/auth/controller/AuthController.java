package solsolsol.mukpick.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solsolsol.mukpick.domain.auth.dto.AuthResponse;
import solsolsol.mukpick.domain.auth.dto.RefreshTokenRequest;
import solsolsol.mukpick.domain.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 카카오 로그인 콜백
     * GET /api/auth/kakao/callback?code=인가코드
     */
    @GetMapping("/kakao/callback")
    public ResponseEntity<AuthResponse> kakaoCallback(@RequestParam String code) {
        return ResponseEntity.ok(authService.kakaoLogin(code));
    }

    /**
     * Access Token 재발급
     * POST /api/auth/reissue
     */
    @PostMapping("/reissue")
    public ResponseEntity<AuthResponse> reissue(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.reissueToken(request.getRefreshToken()));
    }
}
