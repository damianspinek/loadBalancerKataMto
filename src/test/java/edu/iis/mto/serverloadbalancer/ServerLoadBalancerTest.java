package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
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
	
	@Test
	public void balancingOneServer_withOneSlotCapacity_andOneSlotVm_fillsTheServerWithVm(){
		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(VmBuilder.vm().ofSize(1));
		
		balance(aListOfServersWith(theServer),aListOfVmsWith(theVm));
		assertThat(theServer, hadLoadPercentageOf(100.d));
		assertThat("the server should contain vm", theServer.contains(theVm));
	}

	private <T> T a(Builder<T> builder) {
		// TODO Auto-generated method stub
		return builder.build();
	}

	private Vm[] aListOfVmsWith(Vm theVm) {
		// TODO Auto-generated method stub
		return new Vm[]{theVm};
	}

	private void balance(Server[] aListOfServersWith, Vm[] vms) {
		new ServerLoadBalancer().balance(aListOfServersWith, vms);
		
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

	


}
