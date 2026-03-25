package solsolsol.mukpick.dto.Food.getRandomFood;

import lombok.Getter;
import lombok.Setter;
import solsolsol.mukpick.domain.Food;

@Getter
public class GetRandomFoodResDto {

    public final Long foodId;
    public final String name;

    public GetRandomFoodResDto(Food food) {
        this.foodId = food.getId();
        this.name = food.getName();
    }
}
