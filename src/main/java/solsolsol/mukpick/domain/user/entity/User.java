package solsolsol.mukpick.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import solsolsol.mukpick.global.entity.BaseEntity;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String kakaoId;     // 카카오 고유 식별자

    @Column(nullable = false)
    private String nickname;

    private String profileImageUrl; // 카카오 프로필 이미지

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        USER, ADMIN
    }
}
