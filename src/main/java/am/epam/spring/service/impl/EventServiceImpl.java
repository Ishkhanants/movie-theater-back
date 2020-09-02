package am.epam.spring.service.impl;

import am.epam.spring.domain.entity.Event;
import am.epam.spring.domain.entity.User;
import am.epam.spring.repository.EventRepository;
import am.epam.spring.service.EventService;
import am.epam.spring.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Martin Mirzoyan
 */

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) throws NotFoundException {
        Iterator<Event> it = repository.findAll().iterator();

        while(it.hasNext()){
            Event event = it.next();

            if(event.getName().equals(name)){
                return event;
            }
        }

        throw new NotFoundException();
    }

    @Override
    public Event save(@Nonnull Event event) {
        return repository.save(event);
    }

    @Override
    public void remove(@Nonnull Event event) {
        repository.delete(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return repository.findById(id).get();
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        Iterator<Event> it = repository.findAll().iterator();

        List<Event> events = new LinkedList<>();

        while(it.hasNext()){
            events.add(it.next());
        }

        return events;
    }
}
