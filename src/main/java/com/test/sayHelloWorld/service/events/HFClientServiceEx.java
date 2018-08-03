package com.test.sayHelloWorld.service.events;

import com.telcordia.inpac.ws.NPCWebServicePortType;
import com.telcordia.inpac.ws.npcdatatypes.RetResultType;
import com.test.sayHelloWorld.models.HFSoapModel;
import com.test.sayHelloWorld.service.interfaces.HFClientService;

public class HFClientServiceEx implements HFClientService {
	@Override
	public RetResultType processRequest(HFSoapModel hfSoapModel, NPCWebServicePortType npcPortType) {
		RetResultType retResultType = npcPortType.processMessage(hfSoapModel.getAuthDetails(),
				hfSoapModel.getNpcDataType());
		return retResultType;
	}

}
