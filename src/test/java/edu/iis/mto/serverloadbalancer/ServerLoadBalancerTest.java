package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

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

	

	private Server a(ServerBuilder server) {
		
		return server.build();
	}

	private Vm[] anEmptyListOfVms() {
		// TODO Auto-generated method stub
		return new Vm[0];
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers,vms);
		
	}

	private Server[] aListOfServersWith(Server theServer) {
		// TODO Auto-generated method stub
		return new Server[]{theServer};
	}

	private Matcher<? super Server> hadLoadPercentageOf(double expectedLoadPercentage) {
		// TODO Auto-generated method stub
		return new CurrenntLowPercentageMatcher(expectedLoadPercentage);
	}

	private ServerBuilder server() {
		// TODO Auto-generated method stub
		return new ServerBuilder();
	}


}
