package am.epam.spring.service;

import java.time.LocalDateTime;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import am.epam.spring.domain.entity.Event;
import am.epam.spring.domain.entity.Ticket;
import am.epam.spring.domain.entity.User;

/**
 * @author Yuriy Tkach
 */
public interface BookingService {

    /**
     * Getting price when buying all supplied seats for particular event
     * 
     * @param event
     *            Event to get base ticket price, vip seats and other
     *            information
     * @param airDateTime
     *            Date and time of event air
     * @param user
     *            User that buys ticket could be needed to calculate discount.
     *            Can be <code>null</code>
     * @param seats
     *            Set of seat numbers that user wants to buy
     * @return total price
     */
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime airDateTime, @Nullable User user,
            @Nonnull Set<Integer> seats);

    /**
     * Books tickets in internal system. If user is not
     * <code>null</code> in a ticket then booked tickets are saved with it
     * 
     * @param tickets
     *            Set of tickets
     */
    public void bookTickets(@Nonnull Set<Ticket> tickets);

    /**
     * Getting all purchased tickets for event on specific air date and time
     * 
     * @param event
     *            Event to get tickets for
     * @param airDateTime
     *            Date and time of airing of event
     * @return set of all purchased tickets
     */
    public @Nonnull Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event,
                                                            @Nonnull LocalDateTime airDateTime);

}
