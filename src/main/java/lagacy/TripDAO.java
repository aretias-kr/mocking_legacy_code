package lagacy;

import java.util.List;

public class TripDAO {
	public static List<Trip> findTripsByUser(User user) {
		throw new RuntimeException("Not implements yet");
	}
	public List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}
}
