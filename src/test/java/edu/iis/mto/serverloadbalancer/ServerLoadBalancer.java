package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for(Vm vm : vms){
		List<Server> serversWithEnoughCapacity = new ArrayList<Server>();
		for(Server server : servers){
			if(server.canFit(vm)){
				serversWithEnoughCapacity.add(server);
			}
		}
		Server lessLoadedServer = extractLessLoadedServer(serversWithEnoughCapacity);
		if(lessLoadedServer != null){
			lessLoadedServer.addVm(vm);
		}
		}
		
	}

	private Server extractLessLoadedServer(List<Server> serversWithEnoughCapacity) {
		Server lessLoaded = null;
		for(Server server : serversWithEnoughCapacity){
			if(lessLoaded == null || lessLoaded.currentLoadPecentage > server.currentLoadPecentage){
				lessLoaded = server;
			}
		}
		return lessLoaded;
	}

	
}
