package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {

	private int capacity;
	private double initialLoad;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}
	
	public ServerBuilder withCurrentLoadOf(double initialLoad){
		this.initialLoad = initialLoad;
		return this;
	}

	/* (non-Javadoc)
	 * @see edu.iis.mto.serverloadbalancer.Builder#build()
	 */
	public Server build() {
		// TODO Auto-generated method stub
		Server server = new Server(capacity);
		if(initialLoad > 0){
            int expectedLoad = (int) (initialLoad / 100.0d * (double) server.getCapacity());
            server.addVm(VmBuilder.vm().ofSize(expectedLoad).build());
 		}
		
		return server;
	}
	
	public static ServerBuilder server() {
		// TODO Auto-generated method stub
		return new ServerBuilder();
	}

}
