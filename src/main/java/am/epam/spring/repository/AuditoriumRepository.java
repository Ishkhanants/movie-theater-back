package am.epam.spring.repository;

import am.epam.spring.domain.entity.Auditorium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Martin Mirzoyan
 */

@Repository
public interface AuditoriumRepository extends CrudRepository<Auditorium, Long> {
}
