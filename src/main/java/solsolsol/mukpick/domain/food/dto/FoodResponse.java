package solsolsol.mukpick.domain.food.dto;

import lombok.Builder;
import lombok.Getter;
import solsolsol.mukpick.domain.food.entity.Food;

import java.util.List;

@Getter
@Builder
public class FoodResponse {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String cuisineType;  // 한식/양식/중식/일식
    private String spicyLevel;   // 매운/안매운
    private String foodType;     // 밥/면/빵/떡
    private String tempType;     // 뜨거운/차가운
    private List<IngredientResponse> ingredients;
    private List<RecipeStepResponse> recipeSteps;

    public static FoodResponse from(Food food) {
        return FoodResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .description(food.getDescription())
                .imageUrl(food.getImageUrl())
                .cuisineType(food.getCuisineType().name())
                .spicyLevel(food.getSpicyLevel().name())
                .foodType(food.getFoodType().name())
                .tempType(food.getTempType().name())
                .ingredients(food.getIngredients().stream()
                        .map(IngredientResponse::from)
                        .toList())
                .recipeSteps(food.getRecipeSteps().stream()
                        .map(RecipeStepResponse::from)
                        .toList())
                .build();
    }
}
