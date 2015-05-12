package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {
	int capacity;
	public double currentLoadPercentage;
	public Server(int capacity) {
		this.capacity = capacity;
	}
	public boolean contains(Vm theVm) {
		// TODO Auto-generated method stub
		return true;
	}

}
