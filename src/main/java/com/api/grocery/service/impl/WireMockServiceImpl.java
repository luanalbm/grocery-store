package com.api.grocery.service.impl;

import org.springframework.stereotype.Service;

import com.api.grocery.service.WireMockService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

@Service
public class WireMockServiceImpl implements WireMockService {

	private Integer port;
	private WireMockServer wireMockServer;
	
	@Override
	public void initWireMockServer() {
		if(wireMockServer == null || !wireMockServer.isRunning()) {			
			wireMockServer = new WireMockServer(WireMockConfiguration.options()
					.dynamicPort()
					.usingFilesUnderClasspath("wiremock"));
			wireMockServer.start();
			port = wireMockServer.port();
		}
	}

	@Override
	public Integer getPort() {
		return port;
	}
	
	@Override
	public void stopWireMockServer() {
		wireMockServer.stop();
	}
}
