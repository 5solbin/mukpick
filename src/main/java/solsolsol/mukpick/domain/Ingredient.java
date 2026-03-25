package solsolsol.mukpick.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solsolsol.mukpick.domain.mapping.FoodIngredient;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Ingredient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String imageUrl;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodIngredient> foodIngredients;

    @Builder
    public Ingredient(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public void addFoodIngredient(FoodIngredient foodIngredient) {
        this.foodIngredients.add(foodIngredient);
    }
}
