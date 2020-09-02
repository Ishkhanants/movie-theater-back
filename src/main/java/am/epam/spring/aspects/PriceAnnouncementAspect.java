package am.epam.spring.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Martin Mirzoyan
 */

@Aspect
@Component
public class PriceAnnouncementAspect {

    @Before(value = "execution(* am.epam.spring.service.BookingService." +
            "getTicketsPrice(..))")
    public void beforeAdvice() {
        System.out.println("We gonna know price of tickets!!!");
    }
}
