package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLowPercentageMatcher extends TypeSafeMatcher<Server> {

	private double expectedLoadPercentage;
	
	public CurrentLowPercentageMatcher(double expectedLoadPercentage2) {
		this.expectedLoadPercentage = expectedLoadPercentage2;
	}

	public void describeTo(Description description) {
		description.appendText("a server with load percentage of").appendValue(expectedLoadPercentage);
		
	}
	
	@Override
	protected void describeMismatchSafely(Server item,
			Description mismatchDescription) {
		mismatchDescription.appendText("a server with load percentage of").appendValue(item.currentLoadPercentage);
	}

	@Override
	protected boolean matchesSafely(Server item) {
		// TODO Auto-generated method stub
		return equalsDouble(item);
	}
	
	public static CurrentLowPercentageMatcher hadLoadPercentageOf(double expectedLoadPercentage) {
		// TODO Auto-generated method stub
		return new CurrentLowPercentageMatcher(expectedLoadPercentage);
	}

	private boolean equalsDouble(Server item) {
		return item.currentLoadPercentage == this.expectedLoadPercentage || Math.abs(item.currentLoadPercentage-expectedLoadPercentage) < 0.01d;
	}

}
