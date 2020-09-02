package am.epam.spring.domain.entity;

import am.epam.spring.domain.DomainObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Yuriy Tkach
 * @author Martin Mirzoyan
 */

@Getter
@Setter
@Entity
@Table(name = "AUDITORIUMS")
public class Auditorium extends DomainObject {

    private String name;

    private int numberOfSeats;

    @ElementCollection
    private Set<Integer> vipSeats;

    {
        vipSeats = Collections.emptySet();
    }


    public Auditorium(){}

    public Auditorium(String name, int numberOfSeats, Set<Integer> vipSeats){
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
    }

    /**
     * Counts how many vip seats are there in supplied <code>seats</code>
     * 
     * @param seats
     *            Seats to process
     * @return number of vip seats in request
     */
    public long countVipSeats(Collection<Long> seats) {
        return seats.stream().filter(seat -> vipSeats.contains(seat)).count();
    }

    public Set<Integer> getAllSeats() {
        return IntStream.range(1, numberOfSeats + 1).boxed().collect(Collectors.toSet());
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
        Auditorium other = (Auditorium) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
