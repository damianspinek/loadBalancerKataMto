package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for(Vm vm : vms){
		List<Server> serversWithEnoughCapacity = new ArrayList<Server>();
		findServersWithEnoughCapacity(servers, vm, serversWithEnoughCapacity);
		Server lessLoadedServer = extractLessLoadedServer(serversWithEnoughCapacity);
		if(lessLoadedServer != null){
			lessLoadedServer.addVm(vm);
		}
		}
		
	}

	private void findServersWithEnoughCapacity(Server[] servers, Vm vm,
			List<Server> serversWithEnoughCapacity) {
		for(Server server : servers){
			if(server.canFit(vm)){
				serversWithEnoughCapacity.add(server);
			}
		}
	}

	private Server extractLessLoadedServer(List<Server> serversWithEnoughCapacity) {
		Server lessLoaded = null;
		for(Server server : serversWithEnoughCapacity){
			if(lessLoaded == null || lessLoaded.getCurrentLoadPecentage() > server.getCurrentLoadPecentage()){
				lessLoaded = server;
			}
		}
		return lessLoaded;
	}

	
}
