package legacy;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import lagacy.Trip;
import lagacy.TripService;
import lagacy.User;
import lagacy.UserBuilder;
import lagacy.UserNotLoggedInException;

import org.junit.Before;
import org.junit.Test;

public class TripServiceTest {
	private static final User GUEST = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTERED_USER = new User();
	private static final User ANOTHER_USER = new User();

	private static final Trip TO_KOREA = new Trip();
	private static final Trip TO_PARIS = new Trip();

	private User loggedInUser;
	private TripService tripService;

	@Before
	public void setup() {
		tripService = new TestableTripSerivce();
		
		loggedInUser = REGISTERED_USER;
	}

	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_an_exception_when_user_is_not_logged_in()
			throws Exception {
		loggedInUser = GUEST;
		tripService.getTripsByUser(UNUSED_USER);
	}

	@Test
	public void should_not_return_any_trips_when_users_are_not_friends()
			throws Exception {
		User friend = UserBuilder.aUser()
				.friendsWith(ANOTHER_USER)
				.withTrips(TO_KOREA)
				.build();
		
		List<Trip> tripsByUser = tripService.getTripsByUser(friend);
		assertThat(tripsByUser.size(), is(0));
	}

	@Test
	public void should_return_friend_trips_when_users_are_friends()
			throws Exception {
		
		User friend = UserBuilder.aUser()
				.friendsWith(ANOTHER_USER, loggedInUser)
				.withTrips(TO_KOREA, TO_PARIS)
				.build();

		List<Trip> tripsByUser = tripService.getTripsByUser(friend);
		assertThat(tripsByUser.size(), is(2));
	}

	private class TestableTripSerivce extends TripService {
		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}

		@Override
		protected List<Trip> findTripsByUser(User user) {
			return user.getTrips();
		}
	}

}
