package am.epam.spring.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import am.epam.spring.domain.entity.Auditorium;
import am.epam.spring.domain.entity.Event;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Yuriy Tkach
 */

public class TestEvent {

	private Event event;

	@Before
	public void initEvent() {
		event = new Event();
		event.setBasePrice(1.1);
		event.setName("aaa");
		event.setRating(Rating.HIGH);
	
		LocalDateTime now = LocalDateTime.now();
		
		event.addAirDateTime(now);
		event.addAirDateTime(now.plusDays(1));
		event.addAirDateTime(now.plusDays(2));
	}
	
	@Test
	public void testAddRemoveAirDates() {
		int size = event.getAirDateTimes().size();
		
		LocalDateTime date = LocalDateTime.now().plusDays(5);
		
		event.addAirDateTime(date);
		
		assertEquals(size+1, event.getAirDateTimes().size());
		assertTrue(event.getAirDateTimes().contains(date));
		
		event.removeAirDateTime(date);
		
		assertEquals(size, event.getAirDateTimes().size());
		assertFalse(event.getAirDateTimes().contains(date));
	}
	
	@Test
	public void testCheckAirDates() {
		assertTrue(event.airsOnDate(LocalDate.now()));
		assertTrue(event.airsOnDate(LocalDate.now().plusDays(1)));
		assertFalse(event.airsOnDate(LocalDate.now().minusDays(10)));
		
		assertTrue(event.airsOnDates(LocalDate.now(), LocalDate.now().plusDays(10)));
		assertTrue(event.airsOnDates(LocalDate.now().minusDays(10), LocalDate.now().plusDays(10)));
		assertTrue(event.airsOnDates(LocalDate.now().plusDays(1), LocalDate.now().plusDays(1)));
		assertFalse(event.airsOnDates(LocalDate.now().minusDays(10), LocalDate.now().minusDays(5)));
		
		LocalDateTime time = LocalDateTime.now().plusHours(4);
		event.addAirDateTime(time);
		assertTrue(event.airsOnDateTime(time));
		time = time.plusHours(30);
		assertFalse(event.airsOnDateTime(time));
	}
	
	@Test
	public void testAddRemoveAuditoriums() {
		LocalDateTime time = event.getAirDateTimes().iterator().next();
		
		assertTrue(event.getEventAuditoriumMap().isEmpty());
		
		event.assignAuditorium(time, new Auditorium());
		
		assertFalse(event.getEventAuditoriumMap().isEmpty());
		
		event.removeAuditoriumAssignment(time);
		
		assertTrue(event.getEventAuditoriumMap().isEmpty());
	}
	
	@Test
	public void testAddRemoveAuditoriumsWithAirDates() {
		LocalDateTime time = LocalDateTime.now().plusDays(10);
		
		assertTrue(event.getEventAuditoriumMap().isEmpty());
		
		event.addAirDateTime(time, new Auditorium());
		
		assertFalse(event.getEventAuditoriumMap().isEmpty());
		
		event.removeAirDateTime(time);
		
		assertTrue(event.getEventAuditoriumMap().isEmpty());
	}
	
	@Test
	public void testNotAddAuditoriumWithoutCorrectDate() {
		LocalDateTime time = LocalDateTime.now().plusDays(10);
		
		boolean result = event.assignAuditorium(time, new Auditorium());
		
		assertFalse(result);
		assertTrue(event.getEventAuditoriumMap().isEmpty());
		
		result = event.removeAirDateTime(time);
		assertFalse(result);
		
		assertTrue(event.getEventAuditoriumMap().isEmpty());
	}

}
