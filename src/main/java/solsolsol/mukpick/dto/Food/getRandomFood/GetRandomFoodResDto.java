package solsolsol.mukpick.dto.Food.getRandomFood;

import lombok.Getter;
import lombok.Setter;
import solsolsol.mukpick.domain.Food;

@Getter @Setter
public class GetRandomFoodResDto {

    public Long foodId;
    public String name;

    public GetRandomFoodResDto(Food food) {
        this.foodId = food.getId();
        this.name = food.getName();
    }
}
