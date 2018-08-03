package com.test.sayHelloWorld.service.config;

import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.telcordia.inpac.ws.NPCWebServicePortType;
import com.test.sayHelloWorld.services.HFSoapServices;
import com.test.sayHelloWorld.utils.SoapDefineUtils;

@Configuration
public class HFSoapServiceConfig {

	@Autowired
	private Bus bus = null;

	@Value("${" + SoapDefineUtils.SOAP_NAME + "}")
	private String SOAP_ENDPOINT_PUBLISH = "";

	@Value("${" + SoapDefineUtils.LOCATION_URI + "}")
	private String SOAP_SERVLET = "";

	@Value("${" + SoapDefineUtils.SOAP_WSDL_FILE + "}")
	private String SOAP_WSDL_FILE = "";

	@Value("${" + SoapDefineUtils.SOAP_EVENT_LISTENER_CLASS + "}")
	private String EVENT_LISTENER_CLASS = "";

	@Value("${" + SoapDefineUtils.SOAP_VALIDATE_LISTENER_CLASS + "}")
	private String VALIADTE_LISTENER_CLASS = "";

	@Value("${" + SoapDefineUtils.SOAP_CLIENT_LISTENER_CLASS + "}")
	private String CLIENT_LISTENER_CLASS = "";

	@Bean("endpointHF")
	public Endpoint endpoint() throws Exception {
		HFSoapServices hfService = new HFSoapServices();
		hfService.setNPCPortType(createNPCPortType());
		hfService.setListenerClass(EVENT_LISTENER_CLASS, VALIADTE_LISTENER_CLASS, CLIENT_LISTENER_CLASS);

		EndpointImpl endpoint = new EndpointImpl(bus, hfService, SOAPBinding.SOAP12HTTP_BINDING);
		endpoint.publish(this.SOAP_ENDPOINT_PUBLISH);
		endpoint.setWsdlLocation(this.SOAP_WSDL_FILE);
		return endpoint;
	}

	public NPCWebServicePortType createNPCPortType() throws Exception {
		return null;
	}

}
