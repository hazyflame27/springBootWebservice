package com.test.sayHelloWorld.service.interfaces;

import com.telcordia.inpac.ws.npcdatatypes.RetResultType;
import com.test.sayHelloWorld.models.HFSoapModel;

public interface HFValidateInput {
	public RetResultType validateInput(HFSoapModel hfSoapModel);
}
