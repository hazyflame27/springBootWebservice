package com.test.sayHelloWorld.models;

import com.telcordia.inpac.ws.npcdatatypes.AuthDetailsType;
import com.telcordia.inpac.ws.npcdatatypes.NPCDataType;
import com.telcordia.inpac.ws.npcdatatypes.RetResultType;

public class HFSoapModel {
	private AuthDetailsType authDetails;
	private RetResultType retResultType;
	private NPCDataType npcDataType;

	public AuthDetailsType getAuthDetails() {
		return authDetails;
	}

	public void setAuthDetails(AuthDetailsType authDetails) {
		this.authDetails = authDetails;
	}

	public RetResultType getRetResultType() {
		return retResultType;
	}

	public void setRetResultType(RetResultType retResultType) {
		this.retResultType = retResultType;
	}

	public NPCDataType getNpcDataType() {
		return npcDataType;
	}

	public void setNpcDataType(NPCDataType npcDataType) {
		this.npcDataType = npcDataType;
	}

}
