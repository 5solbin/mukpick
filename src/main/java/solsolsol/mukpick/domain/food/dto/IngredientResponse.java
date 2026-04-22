package solsolsol.mukpick.domain.food.dto;

import lombok.Builder;
import lombok.Getter;
import solsolsol.mukpick.domain.food.entity.Ingredient;

@Getter
@Builder
public class IngredientResponse {

    private String name;
    private String amount;

    public static IngredientResponse from(Ingredient ingredient) {
        return IngredientResponse.builder()
                .name(ingredient.getName())
                .amount(ingredient.getAmount())
                .build();
    }
}
