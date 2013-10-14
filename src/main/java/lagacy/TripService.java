package lagacy;

import java.util.ArrayList;
import java.util.List;

public class TripService {
	public List<Trip> getTripsBy(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();
		
		if (!isLoggedIn(loggedUser)) {
			throw new UserNotLoggedInException();
		}
		
		if (user.isFriendWith(loggedUser)) {
			return findTripsByUser(user);
		}
		return noTrips();
	}
	protected List<Trip> findTripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}
	protected User getLoggedUser() {
		return UserSession.getInstace().getLoggedUser();
	}
	
	private boolean isLoggedIn(User loggedUser) {
		return loggedUser == null;
	}

	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

}
