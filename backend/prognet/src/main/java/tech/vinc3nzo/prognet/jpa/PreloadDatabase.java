package tech.vinc3nzo.prognet.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.vinc3nzo.prognet.jpa.models.User;
import tech.vinc3nzo.prognet.jpa.repositories.UserRepository;

@Configuration
public class
PreloadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(PreloadDatabase.class);
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            var user1 = new User("Pavel Semenov First", "Backend developer",
                    "p.semenov@example.com", encoder.encode("semenov2022"),
                    "p.semenov");
            var user2 = new User("Harry Potter James", "I am a good guy",
                    "hairypotter@example.com", encoder.encode("thepasscode"),
                    "h.potter");
            var user3 = new User("Erich Maria", "Remarque", "", "Frontend developer, namesake",
                    "em.remarque@example.com", encoder.encode("theKey"),
                    "remarque.frontend");
            var user4 = new User("Barry Berry", "System administrator",
                    "no.time.to.waste@example.com", encoder.encode("FullControl"),
                    "lets_gooo");
            var user5 = new User("Evgeny", "Backend developer",
                    "evgeny@example.com", encoder.encode("lol_no_password_for_you"),
                    "evgeny.dev");
            var user6 = new User("Ilya", "Frontend developer",
                    "ilya@example.com", encoder.encode("no_password2"),
                    "ilya.dev");
            var user7 = new User("Max", "Frontend developer, designer",
                    "max@example.com", encoder.encode("no_password3"),
                    "max.dev");

            user1.getContacts().setYoutube("https://www.youtube.com/c/AtomicShrimp");
            user1.getContacts().setGithub("https://github.com/yuk7/wsldl");

            repository.save(user1);
            repository.save(user2);
            repository.save(user3);
            repository.save(user4);
            repository.save(user5);
            repository.save(user6);
            repository.save(user7);

            logger.info("Preloaded the database with a few dummy Users.");
        };
    }
}
