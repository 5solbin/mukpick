package solsolsol.mukpick.domain.food.dto;

import lombok.Builder;
import lombok.Getter;
import solsolsol.mukpick.domain.food.entity.RecipeStep;

@Getter
@Builder
public class RecipeStepResponse {

    private int stepOrder;
    private String description;
    private String imageUrl;

    public static RecipeStepResponse from(RecipeStep recipeStep) {
        return RecipeStepResponse.builder()
                .stepOrder(recipeStep.getStepOrder())
                .description(recipeStep.getDescription())
                .imageUrl(recipeStep.getImageUrl())
                .build();
    }
}
