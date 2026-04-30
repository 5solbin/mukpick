package solsolsol.mukpick.domain.food.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solsolsol.mukpick.domain.food.dto.FoodResponse;
import solsolsol.mukpick.domain.food.entity.Food;
import solsolsol.mukpick.domain.food.service.FoodService;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    /**
     * 랜덤 음식 조회 (필터 없음)
     * GET /api/foods/random
     */
    @GetMapping("/random")
    public ResponseEntity<FoodResponse> getRandomFood() {
        return ResponseEntity.ok(foodService.getRandomFood());
    }

    /**
     * 카테고리 필터 적용 랜덤 음식 조회
     * GET /api/foods/random/filter?cuisineType=KOREAN&spicyLevel=SPICY&foodType=NOODLE&tempType=HOT
     * 각 파라미터는 선택값 (null 허용) - 없으면 해당 조건 무시
     */
    @GetMapping("/random/filter")
    public ResponseEntity<FoodResponse> getRandomFoodWithFilter(
            @RequestParam(required = false) Food.CuisineType cuisineType,
            @RequestParam(required = false) Food.SpicyLevel spicyLevel,
            @RequestParam(required = false) Food.FoodType foodType,
            @RequestParam(required = false) Food.TempType tempType
    ) {
        return ResponseEntity.ok(foodService.getRandomFoodWithFilter(cuisineType, spicyLevel, foodType, tempType));
    }

    /**
     * 음식 상세 조회 (추후 구현)
     * GET /api/foods/{id}
     */
}
