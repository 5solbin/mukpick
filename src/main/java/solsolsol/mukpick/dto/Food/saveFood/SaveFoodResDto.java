package solsolsol.mukpick.dto.Food.saveFood;

import lombok.Getter;
import lombok.Setter;

@Getter
public class SaveFoodResDto {
    private final Long foodId;

    public SaveFoodResDto(Long foodId) {
        this.foodId = foodId;
    }
}
