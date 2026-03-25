package solsolsol.mukpick.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solsolsol.mukpick.domain.Ingredient;
import solsolsol.mukpick.dto.Ingredient.SaveIngredientReqDto;
import solsolsol.mukpick.dto.Ingredient.SaveIngredientResDto;
import solsolsol.mukpick.repository.IngredientRepository;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
// check : 커스텀 예외 꼭 만들것
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Transactional
    // check : 동시성 문제
    public SaveIngredientResDto saveIngredient(SaveIngredientReqDto reqDto) {

        // 중복이 있으면 예외 처리
        if (ingredientRepository.findByName(reqDto.getName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 재료 입니다.");
        }

        Ingredient ingredient = Ingredient.builder()
                .name(reqDto.getName())
                .imageUrl(reqDto.getImageUrl())
                .build();

        Ingredient save = ingredientRepository.save(ingredient);

        return new SaveIngredientResDto(save);
    }


}
