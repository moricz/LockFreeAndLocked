package com.licence.execute;

import lockfree.selfOrganizing.SelfOrgList;

public class AddThread extends Thread {

	
	private SelfOrgList sol;
	private int addable;
	
	
	public AddThread(SelfOrgList sol, int value){
		this.sol=sol;
		addable=value;
	}
	
	
	@Override
	public void run() {
		
		sol.add(addable);
		
		
	}
	
	@Override 
	public void start(){
		run();
	}

}
