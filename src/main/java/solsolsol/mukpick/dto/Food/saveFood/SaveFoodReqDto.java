package solsolsol.mukpick.dto.Food.saveFood;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveFoodReqDto {
    private String name;
    private String description;
    private String imageUrl;
    private String recipeContent;
}
