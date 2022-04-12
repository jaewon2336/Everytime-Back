package site.metacoding.everytimeback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EverytimebackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EverytimebackApplication.class, args);
	}

}
