package solsolsol.mukpick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solsolsol.mukpick.domain.Food;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByName(String name);
}
