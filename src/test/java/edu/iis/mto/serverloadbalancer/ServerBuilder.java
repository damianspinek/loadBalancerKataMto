package edu.iis.mto.serverloadbalancer;

public class ServerBuilder {

	private int capacity;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		return new Server(this.capacity);
	}
	
	public static ServerBuilder server() {
		// TODO Auto-generated method stub
		return new ServerBuilder();
	}


}
