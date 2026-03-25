package solsolsol.mukpick.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solsolsol.mukpick.dto.Food.saveFood.SaveFoodReqDto;
import solsolsol.mukpick.dto.Food.saveFood.SaveFoodResDto;
import solsolsol.mukpick.dto.Food.getFood.GetFoodResDto;
import solsolsol.mukpick.dto.Food.getRandomFood.GetRandomFoodResDto;
import solsolsol.mukpick.service.FoodService;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<SaveFoodResDto> saveFood(
            @RequestBody SaveFoodReqDto saveFoodReqDto
    ) {
        SaveFoodResDto response = foodService.saveFood(saveFoodReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<GetFoodResDto> getFood(
            @PathVariable Long foodId
    ) {
        GetFoodResDto response = foodService.getFood(foodId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/random")
    public ResponseEntity<GetRandomFoodResDto> getRandomFood() {
        GetRandomFoodResDto response = foodService.getRandomFood();
        return ResponseEntity.ok(response);
    }
}
