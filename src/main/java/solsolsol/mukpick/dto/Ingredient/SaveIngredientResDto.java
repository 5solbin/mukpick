package solsolsol.mukpick.dto.Ingredient;

import lombok.Getter;
import lombok.Setter;
import solsolsol.mukpick.domain.Ingredient;

@Getter
@Setter
public class SaveIngredientResDto {

    private Long ingredientId;
    private String name;

    public SaveIngredientResDto(Ingredient ingredient) {
        this.ingredientId = ingredient.getId();
        this.name = ingredient.getName();
    }
}
