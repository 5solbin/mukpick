package solsolsol.mukpick.domain.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solsolsol.mukpick.domain.food.repository.FoodRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    private final FoodRepository foodRepository;

    // 랜덤 음식 조회 / 음식 상세 조회 로직은 추후 구현
}
