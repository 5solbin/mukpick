package solsolsol.mukpick.domain.food.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solsolsol.mukpick.domain.food.dto.FoodResponse;
import solsolsol.mukpick.domain.food.service.FoodService;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    /**
     * 랜덤 음식 조회
     * GET /api/foods/random
     */
    @GetMapping("/random")
    public ResponseEntity<FoodResponse> getRandomFood() {
        return ResponseEntity.ok(foodService.getRandomFood());
    }

    // GET /api/foods/{id} - 음식 상세 조회 (추후 구현)
}
