package legacy;

import lagacy.TripService;
import lagacy.User;
import lagacy.UserNotLoggedInException;

import org.junit.Test;

public class TripServiceTest {
	private static final User GUEST = null;
	private static final User UNUSED_USER = null;
	private User loggedInUser;

	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_an_exception_when_user_is_not_logged_in() throws Exception {
		TripService tripService = new TestableTripSerivce();
		loggedInUser = GUEST;
		tripService.getTripsByUser(UNUSED_USER);
	}
	
	private class TestableTripSerivce extends TripService {
		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}
	}
}
