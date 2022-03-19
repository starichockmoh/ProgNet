package tech.vinc3nzo.prognet.jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import tech.vinc3nzo.prognet.jpa.models.Contacts;

public interface ContactsRepository extends CrudRepository<Contacts, Long> {
}
