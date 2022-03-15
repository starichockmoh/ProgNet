package tech.vinc3nzo.prognet.repository;

import org.springframework.data.repository.CrudRepository;
import tech.vinc3nzo.prognet.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
