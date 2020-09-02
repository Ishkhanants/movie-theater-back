package am.epam.spring.service.impl;

import am.epam.spring.domain.Rating;
import am.epam.spring.domain.entity.Event;
import am.epam.spring.domain.entity.Ticket;
import am.epam.spring.domain.entity.User;
import am.epam.spring.repository.TicketRepository;
import am.epam.spring.service.BookingService;
import am.epam.spring.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Martin Mirzoyan
 */

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private DiscountService discountService;

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime airDateTime,
                                  @Nullable User user, @Nonnull Set<Integer> seats) {
        double finalPrice = seats.size() * event.getBasePrice();
        double discount = discountService.getDiscount(user, event, airDateTime, seats.size()) * 0.01;

        Set<Integer> vipSeats = event.getEventAuditoriumMap().get(airDateTime).getVipSeats();

        for (int seat: seats) {
            if(vipSeats.contains(seat)){
                finalPrice += event.getBasePrice();
            }
        }

        if(event.getRating() == Rating.HIGH){
            finalPrice *= 1.2;
        }

        return finalPrice - finalPrice * discount;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        //tickets.forEach(t -> t.getUser().getTickets().add(t));
        repository.saveAll(tickets);
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event,
                                                   @Nonnull LocalDateTime airDateTime) {
        Iterator<Ticket> it = repository.findAll().iterator();

        Set<Ticket> tickets = new HashSet<>();

        while(it.hasNext()){
            Ticket ticket = it.next();

            if(ticket.getDateTime().equals(airDateTime) &&
                    ticket.getEvent().equals(event)){

                tickets.add(ticket);
            }
        }

        return tickets;
    }
}
