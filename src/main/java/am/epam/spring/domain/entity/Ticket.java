package am.epam.spring.domain.entity;

import am.epam.spring.domain.DomainObject;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Yuriy Tkach
 * @author Martin Mirzoyan
 */

@Getter
@Entity
@Table(name = "TICKETS")
public class Ticket extends DomainObject implements Comparable<Ticket> {

    @ManyToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @AttributeOverrides( {
            @AttributeOverride(name="id", column = @Column(name="user_id") ),
    } )
    private User user;

    @ManyToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @AttributeOverrides( {
            @AttributeOverride(name="id", column = @Column(name="event_id") ),
    } )
    private Event event;

    private LocalDateTime dateTime;

    private int seat;

    public Ticket(){}

    public Ticket(User user, Event event, LocalDateTime dateTime, int seat) {
        user.getTickets().add(this);
        this.user = user;
        this.event = event;
        this.dateTime = dateTime;
        this.seat = seat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, event, seat);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ticket other = (Ticket) obj;
        if (dateTime == null) {
            if (other.dateTime != null) {
                return false;
            }
        } else if (!dateTime.equals(other.dateTime)) {
            return false;
        }
        if (event == null) {
            if (other.event != null) {
                return false;
            }
        } else if (!event.equals(other.event)) {
            return false;
        }
        if (seat != other.seat) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Ticket other) {
        if (other == null) {
            return 1;
        }
        int result = dateTime.compareTo(other.getDateTime());

        if (result == 0) {
            result = event.getName().compareTo(other.getEvent().getName());
        }
        if (result == 0) {
            result = Integer.compare(seat, other.getSeat());
        }
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "user=" + user +
                ", event=" + event +
                ", dateTime=" + dateTime +
                ", seat=" + seat +
                '}';
    }
}
