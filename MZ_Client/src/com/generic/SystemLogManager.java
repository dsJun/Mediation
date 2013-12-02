package com.generic;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SystemLogManager implements Serializable {
	
	ArrayList<SystemLog> logList = new ArrayList<SystemLog>();

	public SystemLogManager() {
		super();
	}
	
	
	public void addSystemLog ( SystemLog log){
		
		logList.add(log);
	
	}
	
	
	public ArrayList<SystemLog> getAllSystemLogs(){
		return logList;
		
		
	}
	

}
