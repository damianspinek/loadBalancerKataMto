package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ServerLoadBalancerManyServersManyVmsParametrizedTest extends
		ServerLoadBalancerBaseTest {
	int[] capacities;
	int[] sizes;
	double[] expectedLoads;
	int[][] expectVmOnServer;
	
	@Parameters
	public static Collection<Object[]> addedNumbers() {
		// capacities sizes expectedLoads expectedVmOnServer
		return Arrays
				.asList(new Object[][] {
						{ 
							new int[] { 3, 4 }, 
							new int[] { 1, 2 },
							new double[] { 33.33, 50.0 },
							new int[][] { {0},{ 1} }, 
						},{ 
							new int[] { 2, 5, 6 }, 
							new int[] { 6, 1, 2, 2 },
							new double[] { 50.0, 80.0, 100.0 },
							new int[][] { {1},{ 2, 3},{ 0} } 
						}});
	}
	
	public ServerLoadBalancerManyServersManyVmsParametrizedTest(int[] capacity,
			int[] size, double[] expected, int[][] expectVmOnServer) {
		this.capacities = capacity;
		this.sizes = size;
		this.expectedLoads = expected;
		this.expectVmOnServer = expectVmOnServer;
	}

	

	@Test
	public void balance_serversAndVms() {
		Server[] servers = new Server[capacities.length];
		for (int i = 0; i < capacities.length; i++) {
			servers[i] = a(server().withCapacity(capacities[i]));
		}

		Vm[] vms = new Vm[sizes.length];
		for (int i = 0; i < sizes.length; i++) {
			vms[i] = a(vm().ofSize(sizes[i]));
		}

		balance(servers, vms);

		for (int i = 0; i < servers.length; i++) {
			assertThat(servers[i], hasLoadPercentageOf(expectedLoads[i]));
			for (int j = 0; j < expectVmOnServer[i].length; j++) {
				assertThat("the server contain vm" + expectVmOnServer[i][j],
						servers[i].contains(vms[expectVmOnServer[i][j]]));
	
			}
		}
	}

}
