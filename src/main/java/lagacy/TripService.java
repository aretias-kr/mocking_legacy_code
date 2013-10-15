package lagacy;

import java.util.ArrayList;
import java.util.List;

public class TripService {
	private TripDAO tripDAO;

	public List<Trip> getTripsBy(User user, User loggedUser) throws UserNotLoggedInException {
		if (isNotLoggedIn(loggedUser)) {
			throw new UserNotLoggedInException();
		}
		
		if (user.isFriendWith(loggedUser)) {
			return findTripsByUser(user);
		}
		return noTrips();
	}
	protected List<Trip> findTripsByUser(User user) {
		return tripDAO.tripsBy(user);
	}
	
	private boolean isNotLoggedIn(User loggedUser) {
		return loggedUser == null;
	}

	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

}
