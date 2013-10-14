package lagacy;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author JeongInn
 */
public class TripServiceTest {
	private static final User NOT_REGISTERED_USER = null;
	public static final User GUEST = null;
	private static final User LEE = new User();
	private static final User REGISTRED_USER = new User();
	private static final Trip JEJUDO = new Trip();
	private static final Trip KYUNGJU = new Trip();

	private TripService sut;
	
	@Before
	public void before() {
		sut = new TestableTripService();
	}
	
	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_exception_when_calling_without_set_logged_in_user() throws Exception {
		loggedUser = GUEST;
		sut.getTripsBy(NOT_REGISTERED_USER);
	}
	
	@Test
	public void should_return_empty_trip_when_user_and_logged_user_is_not_friend() throws Exception {
		loggedUser = REGISTRED_USER;
		
		User user = new User();
		user.addFriend(LEE);
		
		List<Trip> friendTrips = sut.getTripsBy(user);
		assertEquals(0, friendTrips.size());
	}
	
	@Test
	public void should_return_user_trips_when_logged_user_freinds_withuser() throws Exception {
		loggedUser = REGISTRED_USER;
		
		User user = new User();
		user.addFriend(LEE);
		user.addFriend(REGISTRED_USER);
		
		user.addTrip(JEJUDO);
		user.addTrip(KYUNGJU);
		
		List<Trip> friendTrips = sut.getTripsBy(user);
		assertEquals(2, friendTrips.size());
	}
	
	private User loggedUser;
	private class TestableTripService extends TripService {
		@Override
		protected User getLoggedUser() {
			return loggedUser;
		}

		@Override
		protected List<Trip> findTripsByUser(User user) {
			return user.getTrips();
		}
	}
}
