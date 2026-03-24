package solsolsol.mukpick.dto.Food.createFood;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFoodReqDto {
    private String name;
    private String description;
    private String imageUrl;
    private String recipeContent;
}
