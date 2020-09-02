package am.epam.spring.service.impl;

import am.epam.spring.domain.entity.Auditorium;
import am.epam.spring.domain.entity.Event;
import am.epam.spring.repository.AuditoriumRepository;
import am.epam.spring.service.AuditoriumService;
import am.epam.spring.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Martin Mirzoyan
 */

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditoriumRepository repository;

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return (Set<Auditorium>) repository.findAll();
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) throws NotFoundException {
        Iterator<Auditorium> it = repository.findAll().iterator();

        while(it.hasNext()){
            //var is not applicable for return type
            Auditorium auditorium = it.next();

            if(auditorium.getName().equals(name)){
                return auditorium;
            }
        }

        throw new NotFoundException();
    }

    public Auditorium save(@Nonnull Auditorium auditorium) {
        return repository.save(auditorium);
    }
}
