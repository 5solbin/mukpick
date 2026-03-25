package solsolsol.mukpick.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solsolsol.mukpick.domain.Food;
import solsolsol.mukpick.domain.Recipe;
import solsolsol.mukpick.dto.Food.createFood.CreateFoodReqDto;
import solsolsol.mukpick.dto.Food.createFood.CreateFoodResDto;
import solsolsol.mukpick.dto.Food.getFood.GetFoodResDto;
import solsolsol.mukpick.dto.Food.getRandomFood.GetRandomFoodResDto;
import solsolsol.mukpick.repository.FoodRepository;
import solsolsol.mukpick.repository.RecipeRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;

    @Transactional
    public CreateFoodResDto createFood(CreateFoodReqDto createFoodRequestDto) {

        // check : 예외 좀 더 구체적으로 해야 될듯
        if (createFoodRequestDto.getRecipeContent() == null) {
            throw new IllegalArgumentException("레시피 정보가 필요합니다.");
        }

        Food food = Food.builder()
                .name(createFoodRequestDto.getName())
                .description(createFoodRequestDto.getDescription())
                .imageUrl(createFoodRequestDto.getImageUrl())
                .build();

        Food savedFood = foodRepository.save(food);

        Recipe recipe = Recipe.builder()
                .content(createFoodRequestDto.getRecipeContent())
                .build();

        savedFood.setRecipe(recipe);
        recipe.setFood(savedFood);
        recipeRepository.save(recipe);

        return new CreateFoodResDto(savedFood.getId());
    }

    public GetFoodResDto getFood(Long foodId) {

        Food food = foodRepository.findById(foodId).orElseThrow(
                () -> new IllegalArgumentException("음식 정보가 존재하지 않습니다.")
        );
        Recipe foundRecipe = recipeRepository.findByFoodId(foodId);

        return new GetFoodResDto(food, foundRecipe);
    }

    public GetRandomFoodResDto getRandomFood() {
        List<Food> foods = foodRepository.findAll();
        if (foods.isEmpty()) {
            throw new IllegalArgumentException("랜덤으로 뽑을 음식이 없습니다.");
        }

        Food food = foods.get(ThreadLocalRandom.current().nextInt(foods.size()));
        return new GetRandomFoodResDto(food);
    }
}
