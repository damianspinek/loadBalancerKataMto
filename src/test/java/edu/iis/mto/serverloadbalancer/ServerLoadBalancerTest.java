package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLowPercentageMatcher.hadLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.hasVmsCountOf;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
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
		Vm theVm = a(vm().ofSize(1));
		
		balance(aListOfServersWith(theServer),aListOfVmsWith(theVm));
		assertThat(theServer, hadLoadPercentageOf(100.d));
		assertThat("the server should contain vm", theServer.contains(theVm));
	}
	
	@Test
	public void balancingOneServer_withTenSlotCapacity_andOneSlotVm_fillTheServerInTenPercent(){
		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().ofSize(1));
		
		balance(aListOfServersWith(theServer),aListOfVmsWith(theVm));
		
		assertThat("the server should contain vm", theServer.contains(theVm));
		assertThat(theServer, hadLoadPercentageOf(10.0d));
	}
	
	@Test
	public void balancingAServerWithEnoughRoom__getsFilledWithAllVms(){
		Server theServer = a(server().withCapacity(10));
		Vm firstVm = a(vm().ofSize(4));
		Vm secondVm = a(vm().ofSize(4));
		
		balance(aListOfServersWith(theServer), aListOfVmsWith(firstVm,secondVm));
		
		assertThat(theServer, hasVmsCountOf(2));
		assertThat("the server should contain vm", theServer.contains(firstVm));
		assertThat("the server should contain vm", theServer.contains(secondVm));
	}
	
	@Test
	public void aVm_shouldBeBalanced_onLessLoadServer(){
		Server lessBalancedServer = a(server().withCapacity(10).withCurrentLoadOf(70.0d));
		Server moreBalancedServer = a(server().withCapacity(10).withCurrentLoadOf(95.0d));
		
		Vm theVm = a(vm().ofSize(3));
		
		balance(aListOfServersWith(lessBalancedServer, moreBalancedServer), aListOfVmsWith(theVm));
		
		assertThat("a server should contain vm", lessBalancedServer.contains(theVm));		
		
	}
	
	@Test
	public void balanceAServerWithNoEnoughRoom_shouldNotBeFilledWithAVm(){
		Server theServer = a(server().withCapacity(10).withCurrentLoadOf(99.0d));
		Vm theVm = a(vm().ofSize(2));
		
		balance(aListOfServersWith(theServer),aListOfVmsWith(theVm));
		
		assertThat("a server should not contain vm", !theServer.contains(theVm));
	}
	
	@Test
	public void balanceServer_andVms(){
		Server firstServer = a(server().withCapacity(10).withCurrentLoadOf(30.0d));
		Server secondServer = a(server().withCapacity(10).withCurrentLoadOf(70.0d));
		
		Vm firstVm = a(vm().ofSize(5));
		Vm secondVm = a(vm().ofSize(3));
		Vm thirdVm = a(vm().ofSize(2));
		
		balance(aListOfServersWith(firstServer,secondServer),aListOfVmsWith(firstVm,secondVm,thirdVm));
		
		assertThat(firstServer, hadLoadPercentageOf(100.0d));
		assertThat(secondServer, hadLoadPercentageOf(100.0d));
		
		assertThat("a server should contains vm", firstServer.contains(firstVm));
		assertThat("a server should contains vm", secondServer.contains(secondVm));
		assertThat("a server should contains vm", firstServer.contains(thirdVm));
		
		
	}

	

	private <T> T a(Builder<T> builder) {
		// TODO Auto-generated method stub
		return builder.build();
	}

	private Vm[] aListOfVmsWith(Vm ... vms) {
		// TODO Auto-generated method stub
		return vms;
	}

	private void balance(Server[] aListOfServersWith, Vm[] vms) {
		new ServerLoadBalancer().balance(aListOfServersWith, vms);
		
	}	

	private Vm[] anEmptyListOfVms() {
		// TODO Auto-generated method stub
		return new Vm[0];
	}

	private Server[] aListOfServersWith(Server ... servers) {
		// TODO Auto-generated method stub
		return servers;
	}

	


}
