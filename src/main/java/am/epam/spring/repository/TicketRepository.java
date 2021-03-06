package am.epam.spring.repository;

import am.epam.spring.domain.entity.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Martin Mirzoyan
 */

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
