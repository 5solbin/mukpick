package solsolsol.mukpick.domain.food.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import solsolsol.mukpick.domain.food.dto.FoodResponse;
import solsolsol.mukpick.domain.food.service.FoodService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FoodController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class FoodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FoodService foodService;

    @Test
    @DisplayName("GET /api/foods/random - 랜덤 음식을 정상적으로 반환한다")
    void getRandomFood_success() throws Exception {
        FoodResponse response = FoodResponse.builder()
                .id(1L)
                .name("김치찌개")
                .description("얼큰한 김치찌개")
                .imageUrl("https://example.com/kimchi.jpg")
                .category("KOREAN")
                .ingredients(List.of())
                .recipeSteps(List.of())
                .build();

        given(foodService.getRandomFood()).willReturn(response);

        mockMvc.perform(get("/api/foods/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("김치찌개"))
                .andExpect(jsonPath("$.description").value("얼큰한 김치찌개"))
                .andExpect(jsonPath("$.category").value("KOREAN"));
    }

    @Test
    @DisplayName("GET /api/foods/random - 음식이 없으면 400을 반환한다")
    void getRandomFood_notFound() throws Exception {
        given(foodService.getRandomFood())
                .willThrow(new IllegalArgumentException("등록된 음식이 없습니다."));

        mockMvc.perform(get("/api/foods/random"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("등록된 음식이 없습니다."));
    }
}
