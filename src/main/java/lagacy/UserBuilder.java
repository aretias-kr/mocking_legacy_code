package lagacy;

public class UserBuilder {

	private User[] friends;
	private Trip[] trips;

	public static UserBuilder aUser() {
		return new UserBuilder();
	}

	public UserBuilder friendsWith(User... friends) {
		this.friends = friends;
		return this;
	}

	public UserBuilder withTrips(Trip... trips) {
		this.trips = trips;
		return this;
	}

	public User build() {
		User user = new User();
		
		addFriendsTo(user);
		addTripsTo(user);
		
		return user;
	}

	public void addTripsTo(User user) {
		for (Trip trip : trips) {
			user.addTrip(trip);
		}
	}

	public void addFriendsTo(User user) {
		for (User friend : friends) {
			user.addFriend(friend);
		}
	}

}
