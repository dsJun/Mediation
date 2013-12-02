package com.generic;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SystemLog implements Serializable {
	
	private int serverity ;
	private String sWorklfowID;
	private String firstCretDt ;
	
	public SystemLog(int serverity, String sWorklfowID, String firstCretDt) {
		super();
		this.serverity = serverity;
		this.sWorklfowID = sWorklfowID;
		this.firstCretDt = firstCretDt;
	}
	
	
	public int getServerity() {
		return serverity;
	}
	public void setServerity(int serverity) {
		this.serverity = serverity;
	}
	public String getsWorklfowID() {
		return sWorklfowID;
	}
	public void setsWorklfowID(String sWorklfowID) {
		this.sWorklfowID = sWorklfowID;
	}
	public String getFirstCretDt() {
		return firstCretDt;
	}
	public void setFirstCretDt(String firstCretDt) {
		this.firstCretDt = firstCretDt;
	}
	public String toString(){
		return ""+this.sWorklfowID+","+this.firstCretDt;
	}

}
