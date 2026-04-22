package solsolsol.mukpick.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solsolsol.mukpick.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByKakaoId(String kakaoId);
}
