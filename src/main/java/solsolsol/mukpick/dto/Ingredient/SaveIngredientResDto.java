package solsolsol.mukpick.dto.Ingredient;

import lombok.Getter;
import solsolsol.mukpick.domain.Ingredient;

@Getter
public class SaveIngredientResDto {

    private final Long ingredientId;
    private final String name;

    public SaveIngredientResDto(Ingredient ingredient) {
        this.ingredientId = ingredient.getId();
        this.name = ingredient.getName();
    }
}
