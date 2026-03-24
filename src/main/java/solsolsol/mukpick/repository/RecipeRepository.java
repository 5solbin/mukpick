package solsolsol.mukpick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solsolsol.mukpick.domain.Food;
import solsolsol.mukpick.domain.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByFoodId(Long foodId);
}
