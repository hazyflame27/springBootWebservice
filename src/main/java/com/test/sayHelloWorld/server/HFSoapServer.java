package com.test.sayHelloWorld.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.test.sayHelloWorld.utils.SoapDefineUtils;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan({ "${" + SoapDefineUtils.SOAP_BASE_SCAN_DIR + "}" })
public class HFSoapServer {
	private ConfigurableApplicationContext springapp = null;

	public void start() {
		springapp = SpringApplication.run(HFSoapServer.class, "");
	}

	public boolean isRunning() {
		return springapp.isActive() && springapp.isRunning();
	}

	public void stop() {
		if (springapp != null) {
			springapp.close();
			springapp = null;
		}
	}

	public void main(String[] args) {
		springapp = SpringApplication.run(HFSoapServer.class, "");
	}

	public ConfigurableApplicationContext getSpringApp() {
		return springapp;
	}

}
