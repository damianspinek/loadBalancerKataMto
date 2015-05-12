package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}
	
	@Test
	public void balancingServer_withNoVm_ServerStaysEmpty(){
		Server theServer = a(server().withCapacity(1));
		balance(aListOfServersWith(theServer), anEmptyListOfVms());
		assertThat(theServer, hadLoadPercentageOf(0.0d));
	}

	private void balance(Server[] aListOfServersWith, Vm[] vms) {
		new ServerLoadBalancer().balance(aListOfServersWith, vms);
		
	}

	private Server a(ServerBuilder builder) {
		// TODO Auto-generated method stub
		return builder.build();
	}

	private CurrentLowPercentageMatcher hadLoadPercentageOf(double expectedLoadPercentage) {
		// TODO Auto-generated method stub
		return new CurrentLowPercentageMatcher(expectedLoadPercentage);
	}

	

	private Vm[] anEmptyListOfVms() {
		// TODO Auto-generated method stub
		return new Vm[0];
	}

	private Server[] aListOfServersWith(Server theServer) {
		// TODO Auto-generated method stub
		return new Server[]{theServer};
	}

	private ServerBuilder server() {
		// TODO Auto-generated method stub
		return new ServerBuilder();
	}


}
