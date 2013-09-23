package lagacy;

import java.util.ArrayList;
import java.util.List;

public class TripService {
	private TripDAO tripDAO;
	
	public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
		if (loggedInUser == null) {
			throw new UserNotLoggedInException();
		}
		
		if (user.isFriendsWith(loggedInUser)) {
			return findTripsByUser(user);
		}
		return noTrips();
	}

	private List<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	private List<Trip> findTripsByUser(User user) {
		return tripDAO.tripsBy(user);
	}
}
