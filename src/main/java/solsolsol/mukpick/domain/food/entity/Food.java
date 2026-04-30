package solsolsol.mukpick.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;
import solsolsol.mukpick.global.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "foods")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    // 카테고리 1: 요리 종류 (한식/양식/중식/일식)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CuisineType cuisineType;

    // 카테고리 2: 매운 정도
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpicyLevel spicyLevel;

    // 카테고리 3: 음식 타입 (밥/면/빵/떡)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodType foodType;

    // 카테고리 4: 온도
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TempType tempType;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC")
    @Builder.Default
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("stepOrder ASC")
    @Builder.Default
    private List<RecipeStep> recipeSteps = new ArrayList<>();

    public enum CuisineType {
        KOREAN,   // 한식
        WESTERN,  // 양식
        CHINESE,  // 중식
        JAPANESE  // 일식
    }

    public enum SpicyLevel {
        SPICY,     // 매운
        NOT_SPICY  // 안매운
    }

    public enum FoodType {
        RICE,   // 밥
        NOODLE, // 면
        BREAD,  // 빵
        TTEOK   // 떡
    }

    public enum TempType {
        HOT,  // 뜨거운
        COLD  // 차가운
    }
}
