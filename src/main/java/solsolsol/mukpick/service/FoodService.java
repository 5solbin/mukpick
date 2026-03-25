package solsolsol.mukpick.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solsolsol.mukpick.domain.Food;
import solsolsol.mukpick.domain.Ingredient;
import solsolsol.mukpick.domain.Recipe;
import solsolsol.mukpick.domain.mapping.FoodIngredient;
import solsolsol.mukpick.dto.Food.saveFood.SaveFoodResDto;
import solsolsol.mukpick.dto.Food.saveFood.SaveFoodReqDto;
import solsolsol.mukpick.dto.Food.getFood.GetFoodResDto;
import solsolsol.mukpick.dto.Food.getRandomFood.GetRandomFoodResDto;
import solsolsol.mukpick.repository.FoodRepository;
import solsolsol.mukpick.repository.IngredientRepository;
import solsolsol.mukpick.repository.RecipeRepository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    // check : 동시성 문제 해결
    // check : 커스텀 예외 생성
    @Transactional
    public SaveFoodResDto saveFood(SaveFoodReqDto saveFoodReqDto) {

        if (saveFoodReqDto.getRecipeContent() == null) {
            throw new IllegalArgumentException("레시피 정보가 필요합니다.");
        }

        if (foodRepository.findByName(saveFoodReqDto.getName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 요리 입니다.");
        }

        Food food = Food.builder()
                .name(saveFoodReqDto.getName())
                .description(saveFoodReqDto.getDescription())
                .imageUrl(saveFoodReqDto.getImageUrl())
                .build();

        Food savedFood = foodRepository.save(food);

        Recipe recipe = Recipe.builder()
                .content(saveFoodReqDto.getRecipeContent())
                .build();
        recipe.setFood(savedFood);
        savedFood.setRecipe(recipe);
        recipeRepository.save(recipe);

        // 재료 중복 제거
        List<SaveFoodReqDto.IngredientReqDto> uniqueIngredients = saveFoodReqDto.getIngredients().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        SaveFoodReqDto.IngredientReqDto::getName,
                        Function.identity(),
                        (first, second) -> first
                ))
                .values()
                .stream()
                .toList();


        for (SaveFoodReqDto.IngredientReqDto ingredientReqDto : uniqueIngredients) {
            Ingredient ingredient = ingredientRepository.findByName(ingredientReqDto.getName())
                    .orElseGet(() -> ingredientRepository.save(
                            Ingredient.builder()
                                    .name(ingredientReqDto.getName())
                                    .imageUrl(ingredientReqDto.getImageUrl())
                                    .build()
                    ));

            FoodIngredient foodIngredient = new FoodIngredient();

            savedFood.addFoodIngredient(foodIngredient);
            ingredient.addFoodIngredient(foodIngredient);
        }

        return new SaveFoodResDto(savedFood.getId());
    }
    public GetFoodResDto getFood(Long foodId) {

        Food food = foodRepository.findById(foodId).orElseThrow(
                () -> new IllegalArgumentException("음식 정보가 존재하지 않습니다.")
        );
        Recipe foundRecipe = recipeRepository.findByFoodId(foodId);
        List<FoodIngredient> foodIngredients = food.getFoodIngredients();

        return new GetFoodResDto(food, foundRecipe, foodIngredients);
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
