package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {

	private int expectedCount;

	public ServerVmsCountMatcher(int expectedCount) {
		this.expectedCount = expectedCount;
	}

	public void describeTo(Description description) {
		description.appendText("a server with a vms count of").appendValue(
				expectedCount);
	}
	
	
	@Override
	protected void describeMismatchSafely(Server item,
			Description mismatchDescription) {
		mismatchDescription.appendText("a server with a vms count of").appendValue(
				item.vmsCount());
	}

	@Override
	protected boolean matchesSafely(Server item) {
		// TODO Auto-generated method stub
		return item.vmsCount() == expectedCount;
	}
	
	static Matcher<? super Server> hasVmsCountOf(int expectedCount) {
		// TODO Auto-generated method stub
		return new ServerVmsCountMatcher(expectedCount);
	}

}
