package legacy;

import static org.junit.Assert.*;
import lagacy.User;
import lagacy.UserBuilder;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class UserTest {
	private static final User BOB = new User();
	private static final User PAUL = new User();

	@Test
	public void should_inform_when_user_are_not_friends() throws Exception {
		User user = UserBuilder.aUser()
				.friendsWith(BOB)
				.build();
		
		assertThat(user.isFriendsWith(PAUL), CoreMatchers.is(false));
	}
	
	@Test
	public void should_inform_when_user_are_friends() throws Exception {
		User user = UserBuilder.aUser()
				.friendsWith(BOB, PAUL)
				.build();
		
		assertThat(user.isFriendsWith(PAUL), CoreMatchers.is(true));
	}
}
