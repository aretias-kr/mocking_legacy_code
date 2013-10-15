package lagacy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author JeongInn
 */
@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {
	private static final User NOT_REGISTERED_USER = null;
	public static final User GUEST = null;
	private static final User LEE = new User();
	private static final User REGISTRED_USER = new User();
	private static final Trip JEJUDO = new Trip();
	private static final Trip KYUNGJU = new Trip();

	@Mock
	private TripDAO tripDAO;
	
	@InjectMocks
	private TripService realTripService = new TripService();
	
	private TripService sut;
	
	@Before
	public void before() {
		sut = new TestableTripService();
	}
	
	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_exception_when_calling_without_set_logged_in_user() throws Exception {
		realTripService.getTripsBy(NOT_REGISTERED_USER, GUEST);
	}
	
	@Test
	public void should_return_empty_trip_when_user_and_logged_user_is_not_friend() throws Exception {
		User user = new User();
		user.addFriend(LEE);
		
		List<Trip> friendTrips = realTripService.getTripsBy(user, REGISTRED_USER);
		assertEquals(0, friendTrips.size());
	}
	
	@Test
	public void should_return_user_trips_when_logged_user_freinds_withuser() throws Exception {
		User user = new User();
		user.addFriend(LEE);
		user.addFriend(REGISTRED_USER);
		
		user.addTrip(JEJUDO);
		user.addTrip(KYUNGJU);
		
		when(tripDAO.tripsBy(user)).thenReturn(user.getTrips());
		
		List<Trip> friendTrips = realTripService.getTripsBy(user, REGISTRED_USER);
		assertEquals(2, friendTrips.size());
		verify(tripDAO).tripsBy(user);
	}
	
	private class TestableTripService extends TripService {
		@Override
		protected List<Trip> findTripsByUser(User user) {
			return user.getTrips();
		}
	}
}
