package am.epam.spring.service.impl;

import am.epam.spring.domain.entity.User;
import am.epam.spring.repository.UserRepository;
import am.epam.spring.service.UserService;
import am.epam.spring.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * @author Martin Mirzoyan
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User getUserByEmail(@Nonnull String email) throws NotFoundException {
        Iterator<User> it = repository.findAll().iterator();

        while(it.hasNext()){
            User user = it.next();

            if(user.getEmail().equals(email)){
                return user;
            }
        }

        throw new NotFoundException();
    }

    @Override
    public User save(@Nonnull User user) {
        return repository.save(user);
    }

    @Override
    public void remove(@Nonnull User user) {
        repository.delete(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return repository.findById(id).get();
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        Iterator<User> it = repository.findAll().iterator();

        List<User> users = new LinkedList<>();

        while(it.hasNext()){
            users.add(it.next());
        }

        return users;
    }
}
