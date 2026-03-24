package solsolsol.mukpick.domain.mapping;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solsolsol.mukpick.domain.Food;
import solsolsol.mukpick.domain.Ingredient;

@Entity
@Getter
@NoArgsConstructor
public class FoodIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
}
