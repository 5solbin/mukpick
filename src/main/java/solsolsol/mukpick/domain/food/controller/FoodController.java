package solsolsol.mukpick.domain.food.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solsolsol.mukpick.domain.food.service.FoodService;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    // GET /api/foods/random  - 랜덤 음식 조회
    // GET /api/foods/{id}    - 음식 상세 조회
}
