package am.epam.spring.aspects;

import am.epam.spring.domain.entity.Event;

import am.epam.spring.domain.entity.User;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Martin Mirzoyan
 */

@Aspect
@Component
public class LuckyWinnerAspect {

    @Pointcut("execution(* am.epam.spring.service.impl.BookingServiceImpl.getTicketsPrice(..))" +
            "&& args(event, airDateTime, user, seats)")
    private void getTicketsPrice(Event event, LocalDateTime airDateTime, User user,
                                 Set<Integer> seats) {}

    @Around(value = "getTicketsPrice(event, airDateTime, user, seats)",
            argNames = "pjp, event, user, airDateTime, seats")
    public Object getFinalPrice(ProceedingJoinPoint pjp, Event event, User user,
                                LocalDateTime airDateTime, Set<Integer> seats) throws Throwable {

        boolean isLucky = seats.stream().anyMatch(t -> t.equals(t / 10 + 10 * (t % 10)));

        if(isLucky){
            System.out.println("Congratulations, you're our lucky winner!!!");
            return 0;
        }

        return pjp.proceed();
    }
}
