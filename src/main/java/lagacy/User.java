package lagacy;

import java.util.ArrayList;
import java.util.List;

public class User {

	private List<User> friends = new ArrayList<User>();
	private List<Trip> trips = new ArrayList<Trip>();

	public List<User> getFriends() {
		return friends;
	}

	public void addFriend(User friend) {
		friends.add(friend);
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}

	public List<Trip> getTrips() {
		return trips;
	}
}
