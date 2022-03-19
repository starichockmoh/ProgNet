package tech.vinc3nzo.prognet.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.vinc3nzo.prognet.jpa.models.Contacts;
import tech.vinc3nzo.prognet.jpa.models.User;
import tech.vinc3nzo.prognet.jpa.repositories.UserRepository;

@Configuration
public class PreloadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(PreloadDatabase.class);
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            var user1 = new User("Pavel Baretsky First", "Backend developer",
                    "p.baretskiy@example.com", encoder.encode("baretsky2022"),
                    "p.baretsky");
            var user2 = new User("Harry Potter James", "I am a good guy.",
                    "hairypotter@example.com", encoder.encode("thepasscode"),
                    "h.potter");

            logger.info("Preloaded the database with: " + repository.save(user1));
            logger.info("Preloaded the database with: " + repository.save(user2));
        };
    }
}
