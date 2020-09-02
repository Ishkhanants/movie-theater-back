package am.epam.spring.repository;

import am.epam.spring.domain.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Martin Mirzoyan
 */

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}
