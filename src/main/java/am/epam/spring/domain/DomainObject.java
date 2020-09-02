package am.epam.spring.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Yuriy Tkach
 * @author Martin Mirzoyan
 */

@MappedSuperclass
public abstract class DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}
