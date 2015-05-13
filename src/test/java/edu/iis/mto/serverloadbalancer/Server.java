package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {
	public static final double MAX_LOAD = 100.0d;
	int capacity;
	private double currentLoadPercentage;
	private List<Vm> vms = new ArrayList<Vm>();
	public Server(int capacity) {
		this.capacity = capacity;
	}
	public boolean contains(Vm theVm) {
		return vms.contains(theVm);
	}
	public void addVm(Vm vm) {
		this.vms.add(vm);
		this.setCurrentLoadPercentage(this.getCurrentLoadPercentage() + ((double)vm.size/this.capacity)*MAX_LOAD);
		
	}
	public int vmsCount() {
		// TODO Auto-generated method stub
		return vms.size();
	}
	public int getCapacity() {
		// TODO Auto-generated method stub
		return capacity;
	}
	public boolean canFit(Vm vm) {
		// TODO Auto-generated method stub
		return getCurrentLoadPercentage() + ((double)vm.size / (double)this.capacity *MAX_LOAD) <= MAX_LOAD ;
	}
	public double getCurrentLoadPercentage() {
		return currentLoadPercentage;
	}
	public void setCurrentLoadPercentage(double currentLoadPercentage) {
		this.currentLoadPercentage = currentLoadPercentage;
	}

}
