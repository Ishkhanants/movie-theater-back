package am.epam.spring.repository;

import am.epam.spring.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Martin Mirzoyan
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
