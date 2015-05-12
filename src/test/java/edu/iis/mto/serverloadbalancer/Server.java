package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

	public static final double MAX_LOAD = 100.0d;
	public double currentLoadPecentage;
	public int capacity;
	List<Vm> vms = new ArrayList<Vm>();

	public Server(int capacity) {
		this.capacity = capacity;
	}

	public boolean contains(Vm theVm) {
		// TODO Auto-generated method stub
		return vms.contains(theVm);
	}
	
	public void addVm(Vm vm){
		this.vms.add(vm);
		this.currentLoadPecentage = (double)(vm.size / (double )this.capacity) * MAX_LOAD;
	}

	public int vmsCount() {
		// TODO Auto-generated method stub
		return vms.size();
	}

	public double getCapacity() {
		// TODO Auto-generated method stub
		return capacity;
	}

	public boolean canFit(Vm vm) {
		// TODO Auto-generated method stub
        return currentLoadPecentage + ((double)vm.size / (double)this.capacity *MAX_LOAD) <= MAX_LOAD ;

	}
}
