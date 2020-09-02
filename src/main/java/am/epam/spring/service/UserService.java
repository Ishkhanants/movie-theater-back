package am.epam.spring.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import am.epam.spring.domain.entity.User;
import am.epam.spring.service.exception.NotFoundException;

/**
 * @author Yuriy_Tkach
 */
public interface UserService extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    public User getUserByEmail(@Nonnull String email) throws NotFoundException;

}
