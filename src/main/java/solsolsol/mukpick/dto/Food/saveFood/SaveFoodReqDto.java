package solsolsol.mukpick.dto.Food.saveFood;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveFoodReqDto {
    private String name;
    private String description;
    private String imageUrl;
    private String recipeContent;
    private List<IngredientReqDto> ingredients;

    @Getter @Setter
    public static class IngredientReqDto {
        private String name;
        private String imageUrl;
    }
}
