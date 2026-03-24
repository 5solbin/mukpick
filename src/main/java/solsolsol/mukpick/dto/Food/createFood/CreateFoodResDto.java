package solsolsol.mukpick.dto.Food.createFood;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateFoodResDto {
    private Long foodId;

    public CreateFoodResDto(Long foodId) {
        this.foodId = foodId;
    }
}
