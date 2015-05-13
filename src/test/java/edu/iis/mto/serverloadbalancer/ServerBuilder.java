package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.Server.MAX_LOAD;

public class ServerBuilder implements Builder<Server> {

	private int capacity;
	private double initialLoad;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);
		if (initialLoad > 0) {
			int expectedLoad = (int) (initialLoad / 100.0d * (double) server
					.getCapacity());
			server.addVm(VmBuilder.vm().ofSize(expectedLoad).build());
		}
		return server;
	}

	public static ServerBuilder server() {
		// TODO Auto-generated method stub
		return new ServerBuilder();
	}

	public ServerBuilder withCurrentLoadOf(double currentLoad) {
		this.initialLoad = currentLoad;
		return this;
	}

}
