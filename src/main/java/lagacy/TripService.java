package lagacy;

import java.util.ArrayList;
import java.util.List;

public class TripService {
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		if (getLoggedInUser() == null) {
			throw new UserNotLoggedInException();
		}
		
		if (user.isFriendsWith(getLoggedInUser())) {
			return findTripsByUser(user);
		}
		return new ArrayList<Trip>();
	}

	protected List<Trip> findTripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedInUser() {
		return UserSession.getInstace().getLoggedUser();
	}
}
