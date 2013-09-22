package lagacy;

import java.util.ArrayList;
import java.util.List;

public class TripService {
	public List<Trip> getTripsBy(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = UserSession.getInstace().getLoggedUser();
		
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			
			if (isFriend) {
				tripList = TripDAO.findTripsByUser(user);
			}
			
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}
}
