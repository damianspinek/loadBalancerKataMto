package edu.iis.mto.serverloadbalancer;


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
	public void balancingOneServer_withOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm(){
		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));
		balance(aListOfServersWith(theServer),aListOfVmsWith(theVm));
		
		assertThat(theServer, hadLoadPercentageOf(100.0d));
		assertThat("the server should contain vm", theServer.contains(theVm));
	}
	
	@Test
	public void balancingOneServer_withTenSlotCapacity_andOneSlotVm_fillsTheServerWithTenPercent(){
		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().ofSize(1));
		balance(aListOfServersWith(theServer),aListOfVmsWith(theVm));
		
		assertThat(theServer, hadLoadPercentageOf(10.0d));
		assertThat("the server should contain vm", theServer.contains(theVm));
	}
	
	@Test
	public void balancingOneServer_withEnoughRoom_getFiledWithAllVms(){
		Server theServer = a(server().withCapacity(100));
		Vm theFirstVm = a(vm().ofSize(1));
		Vm theSecondVm = a(vm().ofSize(1));
		balance(aListOfServersWith(theServer), aListOfVmsWith(theFirstVm,theSecondVm));
		assertThat(theServer, hasVmsCountOf(2));
		assertThat("the server should contain vm", theServer.contains(theFirstVm));
		assertThat("the server should contain vm", theServer.contains(theSecondVm));
	}
	
	@Test
	public void aVm_shouldBeBalanced_onLessLoadedServerFirst(){
		Server firstServer = a(server().withCapacity(100).withCurrentLoadOf(45.0d));
		Server secondServer = a(server().withCapacity(100).withCurrentLoadOf(50.0d));
		Vm vm = a(vm().ofSize(10));
		
		balance(aListOfServersWith(firstServer,secondServer), aListOfVmsWith(vm));
		assertThat("the less load server should contain vm", firstServer.contains(vm));
	}
	
	@Test
	public void balanceTheServer_withNoEnoughRoom_ShouldNotBeFilledVms(){
		Server theServer = a(server().withCapacity(10).withCurrentLoadOf(90.0d));
		Vm theVm = a(vm().ofSize(2));
		
		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));
		assertThat("the less load server should not contains vm",!theServer.contains(theVm));
	}
	
	@Test
	public void balance_serversAndVms(){
		Server firstServer = a(server().withCapacity(4));
		Server secondServer = a(server().withCapacity(6));
		
		Vm firstVm = a(vm().ofSize(1));
		Vm secondVm = a(vm().ofSize(4));
		Vm thirdVm = a(vm().ofSize(2));
		
		balance(aListOfServersWith(firstServer,secondServer), aListOfVmsWith(firstVm,secondVm,thirdVm));
		
		assertThat("server 1 should contain vm1",firstServer.contains(firstVm));
		assertThat("server 2 should contain vm1",secondServer.contains(secondVm));
		assertThat("server 1 should contain vm1",firstServer.contains(thirdVm));
		
		assertThat(firstServer	, hadLoadPercentageOf(75.0d));
		assertThat(secondServer, hadLoadPercentageOf(66.66d));
		
	}

	

	private Vm[] aListOfVmsWith(Vm ... vms) {
		// TODO Auto-generated method stub
		return vms;
	}

	private <T> T a(Builder<T> builder) {
		// TODO Auto-generated method stub
		return builder.build();
	}

	private Vm[] anEmptyListOfVms() {
		// TODO Auto-generated method stub
		return new Vm[0];
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers,vms);
		
	}

	private Server[] aListOfServersWith(Server ... servers) {
		// TODO Auto-generated method stub
		return servers;
	}

	private Matcher<? super Server> hadLoadPercentageOf(double expectedLoadPercentage) {
		// TODO Auto-generated method stub
		return new CurrenntLowPercentageMatcher(expectedLoadPercentage);
	}
	

	


}
