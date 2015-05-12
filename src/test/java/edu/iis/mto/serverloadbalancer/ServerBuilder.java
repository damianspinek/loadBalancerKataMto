package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {

	private int capacity;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	/* (non-Javadoc)
	 * @see edu.iis.mto.serverloadbalancer.Builder#build()
	 */
	public Server build() {
		return new Server(this.capacity);
	}
	
	public static ServerBuilder server() {
		// TODO Auto-generated method stub
		return new ServerBuilder();
	}


}
