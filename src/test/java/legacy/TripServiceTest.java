package legacy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import lagacy.Trip;
import lagacy.TripDAO;
import lagacy.TripService;
import lagacy.User;
import lagacy.UserBuilder;
import lagacy.UserNotLoggedInException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {
	private static final User GUEST = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTERED_USER = new User();
	private static final User ANOTHER_USER = new User();

	private static final Trip TO_KOREA = new Trip();
	private static final Trip TO_PARIS = new Trip();
	
	@Mock
	private TripDAO tripDAO;
	
	@InjectMocks
	private TripService tripService;
	


	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_an_exception_when_user_is_not_logged_in()
			throws Exception {
		tripService.getTripsByUser(UNUSED_USER, GUEST);
	}

	@Test
	public void should_not_return_any_trips_when_users_are_not_friends()
			throws Exception {
		User friend = UserBuilder.aUser()
				.friendsWith(ANOTHER_USER)
				.withTrips(TO_KOREA)
				.build();
		
		List<Trip> tripsByUser = tripService.getTripsByUser(friend, REGISTERED_USER);
		assertThat(tripsByUser.size(), is(0));
	}

	@Test
	public void should_return_friend_trips_when_users_are_friends()
			throws Exception {
		
		User friend = UserBuilder.aUser()
				.friendsWith(ANOTHER_USER, REGISTERED_USER)
				.withTrips(TO_KOREA, TO_PARIS)
				.build();

		when(tripDAO.tripsBy(friend)).thenReturn(friend.getTrips());
		
		List<Trip> tripsByUser = tripService.getTripsByUser(friend, REGISTERED_USER);
		assertThat(tripsByUser.size(), is(2));
	}

}
