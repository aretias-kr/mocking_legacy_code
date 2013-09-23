package lagacy;

import org.junit.Test;

public class TripDAOTest {
	@Test(expected = RuntimeException.class)
	public void should_throw_exception_when_retrive_trips_by_user() throws Exception {
		User user = new User();
		new TripDAO().tripsBy(user);
	}
}
