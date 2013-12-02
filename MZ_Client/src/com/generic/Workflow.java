package com.generic;


import java.io.Serializable;

@SuppressWarnings("serial")
public class Workflow implements Serializable{
	
	private boolean bStates;
	private String  sWflowName;
	private int iExecutionTime ;
	
	public Workflow (){
		
	}
	
	public Workflow ( String sWflowName , int iTime){
		this.bStates = true;
		this.sWflowName = sWflowName;
		this.iExecutionTime = iTime;
	}
	
	
	public boolean isbStates() {
		return bStates;
	}
	
	public void setbStates(boolean bStates) {
		this.bStates = bStates;
	}
	
	public String getsWflowName() {
		return sWflowName;
	}
	public void setsWflowName(String sWflowName) {
		this.sWflowName = sWflowName;
	}
	public int getiExecutionTime() {
		return iExecutionTime;
	}
	public void setiExecutionTime(int iExecutionTime) {
		this.iExecutionTime = iExecutionTime;
	}
	
	
	

}
