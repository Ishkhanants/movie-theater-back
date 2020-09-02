package am.epam.spring.domain.entity;

import am.epam.spring.domain.DomainObject;
import am.epam.spring.domain.Rating;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Yuriy Tkach
 * @author Martin Mirzoyan
 */

@Getter
@Setter
@Entity
@Table(name = "EVENTS")
public class Event extends DomainObject {

    private String name;

    @ElementCollection
    @Column(name = "AirDateTime")
    private Set<LocalDateTime> airDateTimes;

    private double basePrice;

    private Rating rating;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "event_auditorium_mapping",
            joinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "auditorium_id",
                    referencedColumnName = "id")})
    @MapKeyColumn(name = "air_date_time")
    private Map<LocalDateTime, Auditorium> eventAuditoriumMap;

    {
        eventAuditoriumMap = new HashMap<>();
    }

    public Event(){}

    public Event(String name, Set<LocalDateTime> airDateTimes, double basePrice, Rating rating) {
        this.name = name;
        this.airDateTimes = airDateTimes;
        this.basePrice = basePrice;
        this.rating = rating;
    }

    /**
     * Checks if event is aired on particular <code>dateTime</code> and assigns
     * auditorium to it.
     * 
     * @param dateTime
     *            Date and time of aired event for which to assign
     * @param auditorium
     *            Auditorium that should be assigned
     * @return <code>true</code> if successful, <code>false</code> if event is
     *         not aired on that date
     */
    public boolean assignAuditorium(LocalDateTime dateTime, Auditorium auditorium) {
        if (airDateTimes.contains(dateTime)) {
            eventAuditoriumMap.put(dateTime, auditorium);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes auditorium assignment from event
     * 
     * @param dateTime
     *            Date and time to remove auditorium for
     * @return <code>true</code> if successful, <code>false</code> if not
     *         removed
     */
    public boolean removeAuditoriumAssignment(LocalDateTime dateTime) {
        return eventAuditoriumMap.remove(dateTime) != null;
    }

    /**
     * Add date and time of event air
     * 
     * @param dateTime
     *            Date and time to add
     * @return <code>true</code> if successful, <code>false</code> if already
     *         there
     */
    public boolean addAirDateTime(LocalDateTime dateTime) {
        return airDateTimes.add(dateTime);
    }

    /**
     * Adding date and time of event air and assigning auditorium to that
     * 
     * @param dateTime
     *            Date and time to add
     * @param auditorium
     *            Auditorium to add if success in date time add
     * @return <code>true</code> if successful, <code>false</code> if already
     *         there
     */
    public boolean addAirDateTime(LocalDateTime dateTime, Auditorium auditorium) {
        boolean result = airDateTimes.add(dateTime);
        if (result) {
            eventAuditoriumMap.put(dateTime, auditorium);
        }
        return result;
    }

    /**
     * Removes the date and time of event air. If auditorium was assigned to
     * that date and time - the assignment is also removed
     * 
     * @param dateTime
     *            Date and time to remove
     * @return <code>true</code> if successful, <code>false</code> if not there
     */
    public boolean removeAirDateTime(LocalDateTime dateTime) {
        boolean result = airDateTimes.remove(dateTime);
        if (result) {
            eventAuditoriumMap.remove(dateTime);
        }
        return result;
    }

    /**
     * Checks if event airs on particular date and time
     * 
     * @param dateTime
     *            Date and time to check
     * @return <code>true</code> event airs on that date and time
     */
    public boolean airsOnDateTime(LocalDateTime dateTime) {
        return airDateTimes.stream().anyMatch(dt -> dt.equals(dateTime));
    }

    /**
     * Checks if event airs on particular date
     * 
     * @param date
     *            Date to ckeck
     * @return <code>true</code> event airs on that date
     */
    public boolean airsOnDate(LocalDate date) {
        return airDateTimes.stream().anyMatch(dt -> dt.toLocalDate().equals(date));
    }

    /**
     * Checking if event airs on dates between <code>from</code> and
     * <code>to</code> inclusive
     * 
     * @param from
     *            Start date to check
     * @param to
     *            End date to check
     * @return <code>true</code> event airs on dates
     */
    public boolean airsOnDates(LocalDate from, LocalDate to) {
        return airDateTimes.stream()
                .anyMatch(dt -> dt.toLocalDate().compareTo(from) >= 0 &&
                        dt.toLocalDate().compareTo(to) <= 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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
        Event other = (Event) obj;
        if (name == null) {
            return other.name == null;
        } else {
            return name.equals(other.name);
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", rating=" + rating +
                '}';
    }
}
