package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		if (vms.length > 0) {
			for (Vm vm : vms) {
				List<Server> serversWithEnoughCapacity = extractServersWithEnoughCapacity(
						servers, vm);
				Server lessLoaded = extractLessLoadedServer(serversWithEnoughCapacity);
				if (lessLoaded != null)
					lessLoaded.addVm(vm);

			}
		}
	}

	private List<Server> extractServersWithEnoughCapacity(Server[] servers,
			Vm vm) {
		List<Server> serversWithEnoughCapacity = new ArrayList<Server>(
				servers.length);
		for (Server server : servers) {
			if (server.canFit(vm))
				serversWithEnoughCapacity.add(server);
		}
		return serversWithEnoughCapacity;
	}

	private Server extractLessLoadedServer(
			List<Server> serversWithEnoughCApacity) {
		Server lessLoaded = null;
		for (Server server : serversWithEnoughCApacity) {
			if (lessLoaded == null
					|| lessLoaded.getCurrentLoadPercentage() > server.getCurrentLoadPercentage())
				lessLoaded = server;
		}
		return lessLoaded;
	}
}
