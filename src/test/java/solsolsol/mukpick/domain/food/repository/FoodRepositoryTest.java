package solsolsol.mukpick.domain.food.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import solsolsol.mukpick.domain.food.entity.Food;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FoodRepositoryTest {

    @Autowired
    private FoodRepository foodRepository;

    @BeforeEach
    void setUp() {
        foodRepository.save(Food.builder()
                .name("김치찌개")
                .description("얼큰한 김치찌개")
                .imageUrl("https://example.com/kimchi.jpg")
                .category(Food.Category.KOREAN)
                .build());

        foodRepository.save(Food.builder()
                .name("짜장면")
                .description("고소한 짜장면")
                .imageUrl("https://example.com/jjajang.jpg")
                .category(Food.Category.CHINESE)
                .build());
    }

    @Test
    @DisplayName("랜덤 음식 조회 - 음식이 존재하면 랜덤으로 1개 반환한다")
    void findRandom_success() {
        Optional<Food> result = foodRepository.findRandom();

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isNotBlank();
    }

    @Test
    @DisplayName("랜덤 음식 조회 - 음식이 없으면 empty를 반환한다")
    void findRandom_empty() {
        foodRepository.deleteAll();

        Optional<Food> result = foodRepository.findRandom();

        assertThat(result).isEmpty();
    }
}
