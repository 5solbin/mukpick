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
    private String category;
    private List<IngredientResponse> ingredients;
    private List<RecipeStepResponse> recipeSteps;

    public static FoodResponse from(Food food) {
        return FoodResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .description(food.getDescription())
                .imageUrl(food.getImageUrl())
                .category(food.getCategory().name())
                .ingredients(food.getIngredients().stream()
                        .map(IngredientResponse::from)
                        .toList())
                .recipeSteps(food.getRecipeSteps().stream()
                        .map(RecipeStepResponse::from)
                        .toList())
                .build();
    }
}
