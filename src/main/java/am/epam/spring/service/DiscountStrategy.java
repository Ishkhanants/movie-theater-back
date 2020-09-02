package am.epam.spring.service;

import am.epam.spring.domain.entity.Event;
import am.epam.spring.domain.entity.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

/**
 * @author Martin Mirzoyan
 */
public interface DiscountStrategy {
    byte calculate(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime,
                   int numberOfTickets);
}
