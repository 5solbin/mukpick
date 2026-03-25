package solsolsol.mukpick.dto.Food.saveFood;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SaveFoodResDto {
    private Long foodId;

    public SaveFoodResDto(Long foodId) {
        this.foodId = foodId;
    }
}
