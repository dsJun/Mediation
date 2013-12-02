package com.generic;
import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class WorkflowManager implements Serializable {
	
	/**
	 * 
	 */

	public static int  iWflowCount = 0;
	
	private ArrayList<Workflow> wflowList ;
	
	public WorkflowManager (){
		
		this.wflowList = new ArrayList<Workflow>();
	}
	
	
	public void addWorkflow( Workflow newWorkflow){
		
		wflowList.add(newWorkflow);
		
		iWflowCount = iWflowCount + 1;
	}
	
	public void deleteWorkflow( String name ){
		
		for( Workflow wflow : wflowList){
			
			if ( wflow.getsWflowName().equals(name)){
				
				wflowList.remove(wflow);
				iWflowCount--;	
			}	
		}
	}
	
	
	public ArrayList<Workflow> getAllWorkflow(){
		if ( wflowList == null )
			return null;
		return wflowList;
	}
}
