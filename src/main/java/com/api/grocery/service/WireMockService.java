package com.api.grocery.service;

public interface WireMockService {

	void initWireMockServer();

	void stopWireMockServer();

	Integer getPort();

}
