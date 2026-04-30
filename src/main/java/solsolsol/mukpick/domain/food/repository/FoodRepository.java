package solsolsol.mukpick.domain.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import solsolsol.mukpick.domain.food.entity.Food;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    // 필터 없이 완전 랜덤
    @Query(value = "SELECT * FROM foods ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Food> findRandom();

    // 필터 조합 랜덤 (null이면 해당 조건 무시)
    @Query(value = """
            SELECT * FROM foods
            WHERE (:cuisineType IS NULL OR cuisine_type = :cuisineType)
              AND (:spicyLevel  IS NULL OR spicy_level  = :spicyLevel)
              AND (:foodType    IS NULL OR food_type    = :foodType)
              AND (:tempType    IS NULL OR temp_type    = :tempType)
            ORDER BY RAND()
            LIMIT 1
            """, nativeQuery = true)
    Optional<Food> findRandomWithFilter(
            @Param("cuisineType") String cuisineType,
            @Param("spicyLevel")  String spicyLevel,
            @Param("foodType")    String foodType,
            @Param("tempType")    String tempType
    );
}
