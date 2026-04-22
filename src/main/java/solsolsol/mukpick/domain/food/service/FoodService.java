package solsolsol.mukpick.domain.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solsolsol.mukpick.domain.food.dto.FoodResponse;
import solsolsol.mukpick.domain.food.entity.Food;
import solsolsol.mukpick.domain.food.repository.FoodRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodResponse getRandomFood() {
        Food food = foodRepository.findRandom()
                .orElseThrow(() -> new IllegalArgumentException("등록된 음식이 없습니다."));
        return FoodResponse.from(food);
    }
}
