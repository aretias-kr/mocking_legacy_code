package legacy;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import lagacy.Trip;
import lagacy.TripService;
import lagacy.User;
import lagacy.UserNotLoggedInException;

import org.junit.Before;
import org.junit.Test;

public class TripServiceTest {
	private static final User GUEST = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTERED_USER = new User();
	private static final User ANOTHER_USER = new User();
	
	private static final Trip TO_KOREA = new Trip();
	
	private User loggedInUser;
	private TripService tripService;

	@Before
	public void setup() {
		tripService = new TestableTripSerivce();
	}
	
	@Test(expected = UserNotLoggedInException.class) public void 
	should_throw_an_exception_when_user_is_not_logged_in() throws Exception {
		loggedInUser = GUEST;
		tripService.getTripsByUser(UNUSED_USER);
	}
	
	@Test
	public void 
	should_not_return_any_trips_when_users_are_not_friends() throws Exception {
		loggedInUser = REGISTERED_USER;
		
		User friend = new User();
		friend.addFriend(ANOTHER_USER);
		friend.addTrip(TO_KOREA);
		
		List<Trip> tripsByUser = tripService.getTripsByUser(friend);
		assertThat(tripsByUser.size(), is(0));
	}

	private class TestableTripSerivce extends TripService {
		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}
	}
}
