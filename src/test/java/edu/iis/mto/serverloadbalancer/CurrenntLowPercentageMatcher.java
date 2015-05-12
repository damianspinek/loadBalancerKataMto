package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrenntLowPercentageMatcher extends TypeSafeMatcher<Server> {

	private double expectedLoadPercentage;

	public CurrenntLowPercentageMatcher(double expectedLoadPercentage) {
		this.expectedLoadPercentage = expectedLoadPercentage;
	}
	@Override
	protected void describeMismatchSafely(Server item,
			Description mismatchDescription) {
		mismatchDescription.appendText("a server with load percentage of ")
		.appendValue(item.getCurrentLoadPecentage());
	}
	
	public void describeTo(Description description) {
		description.appendText("a server with load percentage of ")
				.appendValue(expectedLoadPercentage);

	}

	@Override
	protected boolean matchesSafely(Server server) {
        return equalsDouble(server);

	}
	private boolean equalsDouble(Server server) {
		return server.getCurrentLoadPecentage() == expectedLoadPercentage || Math.abs(server.getCurrentLoadPecentage() - expectedLoadPercentage) < 0.01d;
	}

}
