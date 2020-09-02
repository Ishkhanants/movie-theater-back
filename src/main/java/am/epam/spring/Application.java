package am.epam.spring;

import am.epam.spring.domain.Rating;
import am.epam.spring.domain.entity.Auditorium;
import am.epam.spring.domain.entity.Event;
import am.epam.spring.domain.entity.Ticket;
import am.epam.spring.domain.entity.User;

import am.epam.spring.service.BookingService;

import am.epam.spring.service.UserService;
import am.epam.spring.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Martin Mirzoyan
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;

    public static Set<LocalDateTime> populate(){
        LocalDateTime air1 = LocalDateTime.of(2020, 7, 31, 19, 0);
        LocalDateTime air2 = LocalDateTime.of(2020, 8, 2, 19, 0);
        LocalDateTime air3 = LocalDateTime.of(2020, 8, 4, 19, 0);

        return new HashSet<>(Arrays.asList(air1, air2, air3));
    }

    public static Set<Ticket> populate(User user, Event event, LocalDateTime air1){
        Ticket ticket1 = new Ticket(user, event, air1, 22);
        Ticket ticket2 = new Ticket(user, event, air1, 23);
        Ticket ticket3 = new Ticket(user, event, air1, 52);
        Ticket ticket4 = new Ticket(user, event, air1, 53);
        Ticket ticket5 = new Ticket(user, event, air1, 11);
        Ticket ticket6 = new Ticket(user, event, air1, 12);
        Ticket ticket7 = new Ticket(user, event, air1, 13);
        Ticket ticket8 = new Ticket(user, event, air1, 14);
        Ticket ticket9 = new Ticket(user, event, air1, 15);
        Ticket ticket10 = new Ticket(user, event, air1, 16);

        return new HashSet<>(Arrays.asList(ticket1, ticket2,
                ticket3, ticket4, ticket5, ticket6, ticket7, ticket8, ticket9,
                ticket10));
    }

    public static void main (String[]args){
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws NotFoundException {
        User user = new User("Martin", "Mirzoyan", "ishkhanants@gmail.com",
                LocalDate.of(2000, 7,31));

        Set<LocalDateTime> dateTimeSet = populate();

        LocalDateTime air1 = dateTimeSet.iterator().next();

        Set<Integer> vipSeatSet = new HashSet<>();
        for (int i = 40; i < 61; ++i) {
            vipSeatSet.add(i);
        }

        Auditorium auditorium = new Auditorium("Erebuni", 100, vipSeatSet);

        Event event = new Event("Inception", dateTimeSet, 5.0, Rating.HIGH);
        event.assignAuditorium(air1, auditorium);

        Set<Ticket> tickets = populate(user, event, air1);

        double price = bookingService.getTicketsPrice(event, air1, user,
                new HashSet<>(Arrays.asList(10, 23, 52, 53, 23, 12, 13, 14, 15, 16)));

        System.out.println(price);

        bookingService.bookTickets(tickets);
        bookingService.getPurchasedTicketsForEvent(event, air1).forEach(System.out::println);

        System.out.println(userService.getUserByEmail("ishkhanants@gmail.com"));
    }
}