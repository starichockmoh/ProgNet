package tech.vinc3nzo.prognet.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.vinc3nzo.prognet.jpa.models.User;

import java.util.Objects;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    default Optional<User> findByUsername(String username) {
        return this.findAll().parallelStream()
                .filter(u -> Objects.equals(u.getUsername(), username))
                .findFirst();
    }
}
