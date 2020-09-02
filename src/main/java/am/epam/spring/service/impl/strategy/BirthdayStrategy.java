package am.epam.spring.service.impl.strategy;

import am.epam.spring.domain.entity.Event;
import am.epam.spring.domain.entity.User;
import am.epam.spring.service.DiscountStrategy;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Martin Mirzoyan
 */

@Component
public class BirthdayStrategy implements DiscountStrategy {
    private static final int INTERVAL_BY_DAYS = 5;
    private static final int DISCOUNT_PERCENT = 5;

    @Override
    public byte calculate (@Nullable User user, @Nonnull Event event,
                          @Nonnull LocalDateTime airDateTime, int numberOfTickets) {
        LocalDate birthDate = user.getDob().minusYears(user.getDob().getYear());
        LocalDate airDate = airDateTime.minusYears(airDateTime.getYear()).toLocalDate();

        if(birthDate.minusDays(INTERVAL_BY_DAYS).isBefore(airDate) &&
                birthDate.plusDays(INTERVAL_BY_DAYS).isAfter(airDate)){
            return DISCOUNT_PERCENT;
        }

        return 0;
    }
}