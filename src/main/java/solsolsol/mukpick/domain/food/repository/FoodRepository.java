package solsolsol.mukpick.domain.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import solsolsol.mukpick.domain.food.entity.Food;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query(value = "SELECT * FROM foods ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Food> findRandom();
}
