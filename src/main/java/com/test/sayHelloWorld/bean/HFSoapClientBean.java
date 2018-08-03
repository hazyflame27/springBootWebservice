package com.test.sayHelloWorld.bean;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;

import com.telcordia.inpac.ws.NPCWebServicePortType;

public class HFSoapClientBean {
	public NPCWebServicePortType createNPCPortType(String strAddress, String strKeyStoreFile, String strKeyStorePass)
			throws Exception {
		NPCWebServicePortType npcPortType = null;
		JaxWsProxyFactoryBean jaxWsProxyFactory = new JaxWsProxyFactoryBean();

		jaxWsProxyFactory.setServiceClass(NPCWebServicePortType.class);
		jaxWsProxyFactory.setAddress(strAddress);
		npcPortType = (NPCWebServicePortType) jaxWsProxyFactory.create();
		configureSSLOnTheClient(npcPortType, strKeyStoreFile, strKeyStorePass);
		return npcPortType;
	}

	private void configureSSLOnTheClient(Object c, String keyStorePath, String keyStorePassword) throws Exception {
		Client client = ClientProxy.getClient(c);
		HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
		File truststore = new File(keyStorePath);
		try {
			TLSClientParameters tlsParams = new TLSClientParameters();
			tlsParams.setDisableCNCheck(true);
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(new FileInputStream(truststore), keyStorePassword.toCharArray());
			TrustManagerFactory trustFactory = TrustManagerFactory
					.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			trustFactory.init(keyStore);
			TrustManager[] tm = trustFactory.getTrustManagers();
			tlsParams.setTrustManagers(tm);
			keyStore.load(new FileInputStream(truststore), keyStorePassword.toCharArray());
			KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyFactory.init(keyStore, keyStorePassword.toCharArray());
			KeyManager[] km = keyFactory.getKeyManagers();
			tlsParams.setKeyManagers(km);
			httpConduit.setTlsClientParameters(tlsParams);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
