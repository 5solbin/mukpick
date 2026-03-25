package solsolsol.mukpick.dto.Food.getFood;

import lombok.Getter;
import solsolsol.mukpick.domain.Food;
import solsolsol.mukpick.domain.Recipe;
import solsolsol.mukpick.domain.mapping.FoodIngredient;

import java.util.List;

@Getter
public class GetFoodResDto {

    private final String name;
    private final String description;
    private final String imageUrl;
    private final String recipeContent;
    private final List<FoodIngredientResDto> ingredients;

    public GetFoodResDto(Food food, Recipe recipe, List<FoodIngredient> ingredients) {

        this.description = food.getDescription();
        this.imageUrl = food.getImageUrl();
        this.name = food.getName();
        this.recipeContent = recipe.getContent();
        this.ingredients = ingredients.stream()
                .map(ingredient -> new FoodIngredientResDto(ingredient.getIngredient().getImageUrl(), ingredient.getIngredient().getName()))
                .toList();
    }

    @Getter
    public static class FoodIngredientResDto {
        private final String name;
        private final String imageUrl;

        public FoodIngredientResDto(String imageUrl, String name) {
            this.imageUrl = imageUrl;
            this.name = name;
        }
    }
}
