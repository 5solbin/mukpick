package solsolsol.mukpick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solsolsol.mukpick.domain.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
