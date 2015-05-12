package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
	private int expectedCount;

	public ServerVmsCountMatcher(int expectedCount) {
		this.expectedCount = expectedCount;
	}

	public void describeTo(Description description) {
		description.appendValue("a server with the vms count of ").appendValue(expectedCount);

	}

	@Override
	protected boolean matchesSafely(Server item) {
		return item.vmsCount() ==expectedCount;
	}

	@Override
	protected void describeMismatchSafely(Server item,
			Description mismatchDescription) {
		mismatchDescription.appendText("a server with the vms count of").appendValue(item.vmsCount());
	}
	
	public static ServerVmsCountMatcher hasVmsCountOf(int expectedCount) {
		// TODO Auto-generated method stub
		return new ServerVmsCountMatcher(expectedCount);
	}
}
