package solsolsol.mukpick.domain.food.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import solsolsol.mukpick.domain.food.dto.FoodResponse;
import solsolsol.mukpick.domain.food.entity.Food;
import solsolsol.mukpick.domain.food.repository.FoodRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    @InjectMocks
    private FoodService foodService;

    @Mock
    private FoodRepository foodRepository;

    @Test
    @DisplayName("랜덤 음식 조회 - 정상적으로 음식을 반환한다")
    void getRandomFood_success() {
        Food food = Food.builder()
                .name("김치찌개")
                .description("얼큰한 김치찌개")
                .imageUrl("https://example.com/kimchi.jpg")
                .category(Food.Category.KOREAN)
                .build();

        given(foodRepository.findRandom()).willReturn(Optional.of(food));

        FoodResponse response = foodService.getRandomFood();

        assertThat(response.getName()).isEqualTo("김치찌개");
        assertThat(response.getDescription()).isEqualTo("얼큰한 김치찌개");
        assertThat(response.getCategory()).isEqualTo("KOREAN");
    }

    @Test
    @DisplayName("랜덤 음식 조회 - 음식이 없으면 예외를 던진다")
    void getRandomFood_notFound() {
        given(foodRepository.findRandom()).willReturn(Optional.empty());

        assertThatThrownBy(() -> foodService.getRandomFood())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("등록된 음식이 없습니다.");
    }
}
