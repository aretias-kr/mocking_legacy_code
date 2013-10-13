package lagacy;

import org.junit.Test;

/**
 * @author JeongInn
 */
public class TripServiceTest {
	private static final User NOT_REGISTERED_USER = null;
	public static final User GUEST = null;
	private static final User LEE = new User();

	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_exception_when_calling_without_set_logged_in_user() throws Exception {
		TripService sut = new TestableTripService();
		sut.getTripsBy(NOT_REGISTERED_USER);
	}
	
	@Test
	public void should_return_empty_trip_when_user_and_logged_user_is_not_friend() throws Exception {
		TripService sut = new TestableTripService();
		
		User user = new User();
		user.addFriend(LEE);
		
		sut.getTripsBy(user);
	}
	
	private class TestableTripService extends TripService {
		@Override
		protected User getLoggedUser() {
			return GUEST;
		}
	}
}
