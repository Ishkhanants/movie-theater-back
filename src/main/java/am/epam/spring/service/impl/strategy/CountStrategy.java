package am.epam.spring.service.impl.strategy;

import am.epam.spring.domain.entity.Event;
import am.epam.spring.domain.entity.User;
import am.epam.spring.service.DiscountStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

/**
 * @author Martin Mirzoyan
 */

@Component
public class CountStrategy implements DiscountStrategy {
    private static final int DISCOUNT_PERCENT = 50;
    private static final int COUNT_OF_TICKETS = 10;

    @Override
    public byte calculate(@Nullable User user, @Nonnull Event event,
                          @Nonnull LocalDateTime airDateTime, int numberOfTickets) {

        if(numberOfTickets >= COUNT_OF_TICKETS){
            return DISCOUNT_PERCENT;
        }

        return 0;
    }
}