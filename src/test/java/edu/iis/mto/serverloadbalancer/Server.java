package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	private static final double MAX_LOAD = 100.0d;
	public double currentLoadPecentage;
	public int capacity;

	public Server(int capacity) {
		this.capacity = capacity;
	}

	public boolean contains(Vm theVm) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void addVm(Vm vm){
		this.currentLoadPecentage = (double)(vm.size / (double )this.capacity) * MAX_LOAD;
	}
}
