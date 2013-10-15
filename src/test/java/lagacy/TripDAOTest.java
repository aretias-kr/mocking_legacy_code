package lagacy;

import org.junit.Test;

/**
 * @author JeongInn
 */
public class TripDAOTest {
	@Test(expected = RuntimeException.class)
	public void should_throw_not_implements_yet_exception() throws Exception {
		TripDAO.findTripsByUser(new User());
	}
	
	@Test(expected = RuntimeException.class)
	public void should_throw_not_implements_yet_exception_via_instant_method() throws Exception {
		new TripDAO().tripsBy(new User());
	}
}
