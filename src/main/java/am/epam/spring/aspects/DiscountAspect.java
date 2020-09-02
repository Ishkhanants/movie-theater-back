package am.epam.spring.aspects;

import am.epam.spring.domain.entity.Event;
import am.epam.spring.domain.entity.User;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Martin Mirzoyan
 */

@Aspect
@Component
public class DiscountAspect {
    private int count;

    @Pointcut("execution(* am.epam.spring.service.DiscountService.getDiscount(..))" +
            "&& args(user, event, airDateTime, numberOfTickets)")
    private void getDiscountMethod(User user, Event event, LocalDateTime airDateTime,
                                   int numberOfTickets) {}

    @AfterReturning(pointcut = "getDiscountMethod(user, event, airDateTime, numberOfTickets)",
            returning = "retVal", argNames = "retVal, user, event, airDateTime, numberOfTickets")
    public void afterAdvice(Object retVal, User user, Event event, LocalDateTime airDateTime,
                            int numberOfTickets) {
        Byte discount = (Byte) retVal;

        if(discount > 0){
            count++;

            System.out.printf("Times discount is applied: %d\n", count);
            user.setDiscountTimes(user.getDiscountTimes() + 1);
        }
    }
}
