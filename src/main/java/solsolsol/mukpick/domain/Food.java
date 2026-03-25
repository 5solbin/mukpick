package solsolsol.mukpick.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solsolsol.mukpick.domain.mapping.FoodIngredient;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Food {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    private String imageUrl;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodIngredient> foodIngredients = new ArrayList<>();

    @OneToOne(mappedBy = "food")
    private Recipe recipe;


    @Builder
    public Food(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public void update(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void addFoodIngredient(FoodIngredient foodIngredient) {
        this.foodIngredients.add(foodIngredient);
    }
}
