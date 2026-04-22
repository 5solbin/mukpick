package solsolsol.mukpick.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipe_steps")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class RecipeStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @Column(nullable = false)
    private int stepOrder;      // 단계 순서 (1, 2, 3...)

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description; // 단계 설명

    private String imageUrl;    // 단계별 이미지 (선택)
}
