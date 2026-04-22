package solsolsol.mukpick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MukpickApplication {

    public static void main(String[] args) {
        SpringApplication.run(MukpickApplication.class, args);
    }

}
