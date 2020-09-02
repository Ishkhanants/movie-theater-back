package am.epam.spring.service.impl;

import am.epam.spring.domain.entity.Event;
import am.epam.spring.domain.entity.User;
import am.epam.spring.service.DiscountService;
import am.epam.spring.service.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Martin Mirzoyan
 */

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private List<DiscountStrategy> strategies;

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event,
                            @Nonnull LocalDateTime airDateTime, int numberOfTickets) {

        return strategies.stream().map(e ->
                e.calculate(user, event, airDateTime, numberOfTickets)).reduce((a,b) ->
                a > b ? a : b > a ? b : a).get();
    }
}
