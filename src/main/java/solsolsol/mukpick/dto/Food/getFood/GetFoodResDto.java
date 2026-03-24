package solsolsol.mukpick.dto.Food.getFood;

import lombok.Getter;
import lombok.Setter;
import solsolsol.mukpick.domain.Food;
import solsolsol.mukpick.domain.Recipe;

@Getter
@Setter
public class GetFoodResDto {

    private String name;
    private String description;
    private String imageUrl;
    private String recipeContent;

    public GetFoodResDto(Food food, Recipe recipe) {
        this.description = food.getDescription();
        this.imageUrl = food.getImageUrl();
        this.name = food.getName();
        this.recipeContent = recipe.getContent();
    }
}
