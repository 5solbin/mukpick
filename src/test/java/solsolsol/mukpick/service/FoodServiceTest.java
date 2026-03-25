package solsolsol.mukpick.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import solsolsol.mukpick.domain.Food;
import solsolsol.mukpick.domain.Recipe;
import solsolsol.mukpick.dto.Food.getFood.GetFoodResDto;
import solsolsol.mukpick.dto.Food.getRandomFood.GetRandomFoodResDto;
import solsolsol.mukpick.dto.Food.saveFood.SaveFoodReqDto;
import solsolsol.mukpick.dto.Food.saveFood.SaveFoodResDto;
import solsolsol.mukpick.repository.FoodRepository;
import solsolsol.mukpick.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private FoodService foodService;

    @Test
    @DisplayName("레시피 정보가 없으면 예외가 발생한다")
    void saveFood_whenRecipeContentIsNull_thenThrowException() {
        // given
        SaveFoodReqDto reqDto = new SaveFoodReqDto();
        reqDto.setName("떡볶이");
        reqDto.setDescription("매콤한 음식");
        reqDto.setImageUrl("image-url");
        reqDto.setRecipeContent(null);

        // when & then
        assertThatThrownBy(() -> foodService.saveFood(reqDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("레시피 정보가 필요합니다.");
    }

    @Test
    @DisplayName("음식 저장 시 음식과 레시피가 함께 저장된다")
    void saveFood_success() {
        // given
        SaveFoodReqDto reqDto = new SaveFoodReqDto();
        reqDto.setName("떡볶이");
        reqDto.setDescription("매콤한 음식");
        reqDto.setImageUrl("image-url");
        reqDto.setRecipeContent("레시피 내용");

        Food savedFood = Food.builder()
                .name(reqDto.getName())
                .description(reqDto.getDescription())
                .imageUrl(reqDto.getImageUrl())
                .build();
        ReflectionTestUtils.setField(savedFood, "id", 1L);


        when(foodRepository.save(any(Food.class))).thenReturn(savedFood);
        when(recipeRepository.save(any(Recipe.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        SaveFoodResDto response = foodService.saveFood(reqDto);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getFoodId()).isEqualTo(1L);
        verify(foodRepository, times(1)).save(any(Food.class));
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    @DisplayName("존재하는 음식이면 음식 정보와 레시피를 반환한다")
    void getFood_success() {
        // given
        Long foodId = 1L;

        Food food = Food.builder()
                .name("떡볶이")
                .description("매콤한 음식")
                .imageUrl("image-url")
                .build();
        ReflectionTestUtils.setField(food, "id", foodId);


        Recipe recipe = Recipe.builder()
                .content("레시피 내용")
                .build();

        when(foodRepository.findById(foodId)).thenReturn(Optional.of(food));
        when(recipeRepository.findByFoodId(foodId)).thenReturn(recipe);

        // when
        GetFoodResDto response = foodService.getFood(foodId);

        // then
        assertThat(response).isNotNull();
        verify(foodRepository, times(1)).findById(foodId);
        verify(recipeRepository, times(1)).findByFoodId(foodId);
    }

    @Test
    @DisplayName("존재하지 않는 음식이면 예외가 발생한다")
    void getFood_whenFoodNotFound_thenThrowException() {
        // given
        Long foodId = 1L;
        when(foodRepository.findById(foodId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> foodService.getFood(foodId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음식 정보가 존재하지 않습니다.");

        verify(foodRepository, times(1)).findById(foodId);
        verify(recipeRepository, never()).findByFoodId(anyLong());
    }

    @Test
    @DisplayName("음식 목록이 비어 있으면 예외가 발생한다")
    void getRandomFood_whenFoodListIsEmpty_thenThrowException() {
        // given
        when(foodRepository.findAll()).thenReturn(List.of());

        // when & then
        assertThatThrownBy(() -> foodService.getRandomFood())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("랜덤으로 뽑을 음식이 없습니다.");

        verify(foodRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("음식 목록이 있으면 랜덤 음식이 반환된다")
    void getRandomFood_success() {
        // given
        Food food1 = Food.builder().name("떡볶이").description("매콤한 음식").imageUrl("url1").build();
        ReflectionTestUtils.setField(food1, "id", 1L);


        Food food2 = Food.builder().name("김밥").description("든든한 음식").imageUrl("url2").build();
        ReflectionTestUtils.setField(food2, "id", 2L);

        when(foodRepository.findAll()).thenReturn(List.of(food1, food2));

        // when
        GetRandomFoodResDto response = foodService.getRandomFood();

        // then
        assertThat(response).isNotNull();
        verify(foodRepository, times(1)).findAll();
    }
}