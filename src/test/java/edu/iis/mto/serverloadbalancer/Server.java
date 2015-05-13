package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {
	private static final double MAX_LOAD = 100.0d;
	int capacity;
	public double currentLoadPercentage;
	private List<Vm> vms = new ArrayList<Vm>();
	public Server(int capacity) {
		this.capacity = capacity;
	}
	public boolean contains(Vm theVm) {
		// TODO Auto-generated method stub
		return vms.contains(theVm);
	}
	public void addVm(Vm vm) {
		this.vms.add(vm);
		this.currentLoadPercentage = ((double)vm.size/this.capacity)*MAX_LOAD;
		
	}
	public int vmsCount() {
		// TODO Auto-generated method stub
		return vms.size();
	}
	public int getCapacity() {
		// TODO Auto-generated method stub
		return capacity;
	}

}
