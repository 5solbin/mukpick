package solsolsol.mukpick.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solsolsol.mukpick.domain.enums.Role;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    private Member(String email, String name, String password, Role role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = (role != null) ? role : Role.USER;
    }

    public static Member create(String email, String name, String password) {
        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .role(Role.USER)
                .build();
    }
}
