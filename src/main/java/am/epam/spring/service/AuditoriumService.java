package am.epam.spring.service;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import am.epam.spring.domain.entity.Auditorium;
import am.epam.spring.service.exception.NotFoundException;

/**
 * @author Yuriy Tkach
 */
public interface AuditoriumService {

    /**
     * Getting all auditoriums from the system
     * 
     * @return set of all auditoriums
     */
    public @Nonnull Set<Auditorium> getAll();

    /**
     * Finding auditorium by name
     * 
     * @param name
     *            Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    public @Nullable Auditorium getByName(@Nonnull String name) throws NotFoundException;

}
