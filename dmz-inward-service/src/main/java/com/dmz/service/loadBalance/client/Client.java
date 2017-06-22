package com.dmz.service.loadBalance.client;

public interface Client {

	public void connect() throws Exception;
	public void disConnect() throws Exception;
	
}
