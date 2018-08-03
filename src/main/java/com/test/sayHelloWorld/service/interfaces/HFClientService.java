package com.test.sayHelloWorld.service.interfaces;

import com.telcordia.inpac.ws.NPCWebServicePortType;
import com.telcordia.inpac.ws.npcdatatypes.RetResultType;
import com.test.sayHelloWorld.models.HFSoapModel;

public interface HFClientService {
	public RetResultType processRequest(HFSoapModel hfSoapModel, NPCWebServicePortType npcPortType) ;
	
}
