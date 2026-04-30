package solsolsol.mukpick.domain.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solsolsol.mukpick.domain.food.dto.FoodResponse;
import solsolsol.mukpick.domain.food.entity.Food;
import solsolsol.mukpick.domain.food.repository.FoodRepository;
import solsolsol.mukpick.global.exception.BusinessException;
import solsolsol.mukpick.global.exception.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    private final FoodRepository foodRepository;

    // 필터 없이 완전 랜덤
    public FoodResponse getRandomFood() {
        Food food = foodRepository.findRandom()
                .orElseThrow(() -> new BusinessException(ErrorCode.FOOD_NOT_FOUND));
        return FoodResponse.from(food);
    }

    // 카테고리 필터 적용 랜덤
    public FoodResponse getRandomFoodWithFilter(
            Food.CuisineType cuisineType,
            Food.SpicyLevel spicyLevel,
            Food.FoodType foodType,
            Food.TempType tempType
    ) {
        String cuisineTypeStr = cuisineType != null ? cuisineType.name() : null;
        String spicyLevelStr  = spicyLevel  != null ? spicyLevel.name()  : null;
        String foodTypeStr    = foodType    != null ? foodType.name()    : null;
        String tempTypeStr    = tempType    != null ? tempType.name()    : null;

        Food food = foodRepository.findRandomWithFilter(cuisineTypeStr, spicyLevelStr, foodTypeStr, tempTypeStr)
                .orElseThrow(() -> new BusinessException(ErrorCode.FOOD_NOT_FOUND_WITH_FILTER));
        return FoodResponse.from(food);
    }
}
