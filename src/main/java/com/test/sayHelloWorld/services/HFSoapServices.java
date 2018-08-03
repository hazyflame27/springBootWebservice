package com.test.sayHelloWorld.services;

import com.telcordia.inpac.ws.NPCWebServicePortType;
import com.telcordia.inpac.ws.npcdatatypes.AuthDetailsType;
import com.telcordia.inpac.ws.npcdatatypes.NPCDataType;
import com.telcordia.inpac.ws.npcdatatypes.RetResultType;
import com.test.sayHelloWorld.command.messages.RetResultMessageCode;
import com.test.sayHelloWorld.models.HFSoapModel;
import com.test.sayHelloWorld.service.interfaces.HFClientService;
import com.test.sayHelloWorld.service.interfaces.HFEventListener;
import com.test.sayHelloWorld.service.interfaces.HFValidateInput;

public class HFSoapServices implements NPCWebServicePortType {
	private HFEventListener mnpEventListener = null;
	private HFValidateInput mnpValidateInput = null;
	private HFClientService mnpClientService = null;
	private NPCWebServicePortType npcPortType = null;

	private String listenerClass = "";
	private String ValidateListenerClass = "";
	private String mnpClientServiceClass = "";

	public void setNPCPortType(NPCWebServicePortType npcPortType) {
		this.npcPortType = npcPortType;
	}

	public void setListenerClass(String listenerClass, String ValidateListenerClass, String mnpClientServiceClass)
			throws Exception {
		this.listenerClass = listenerClass;
		this.ValidateListenerClass = ValidateListenerClass;
		this.mnpClientServiceClass = mnpClientServiceClass;
		initListenerClass();
	}

	private Object getClass(String strClassName) throws Exception {
		return Class.forName(strClassName).newInstance();
	}

	private void initListenerClass() throws Exception {
		this.mnpClientService = (HFClientService) getClass(this.mnpClientServiceClass);
		this.mnpValidateInput = (HFValidateInput) getClass(this.ValidateListenerClass);

		try {
			this.mnpEventListener = (HFEventListener) getClass(listenerClass);
		} catch (Exception exp) {
			exp.printStackTrace();
			this.mnpEventListener = null;
		}
	}

	private void setHandlerEvent(HFSoapModel hfSoapModel) {
		try {
			if (this.mnpEventListener != null) {
				this.mnpEventListener.handlerEvent(hfSoapModel);
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Override
	public RetResultType processMessage(AuthDetailsType authDetails, NPCDataType npcMessage) {
		RetResultType retRsType = new RetResultType();
		HFSoapModel hfSoapModel = new HFSoapModel();

		try {
			hfSoapModel.setAuthDetails(authDetails);
			hfSoapModel.setNpcDataType(npcMessage);

			retRsType = this.mnpValidateInput.validateInput(hfSoapModel);
			if (RetResultMessageCode.SUCCESSED_STATUS.equals(retRsType.getResultCode())) {
				// Doan nay de goi den mot API khac
//				retRsType = this.mnpClientService.processRequest(hfSoapModel, npcPortType);
//				if (RetResultMessageCode.SUCCESSED_STATUS.equals(retRsType.getResultCode())) {
//					// goi API xu ly thanh cong
//
//				}
				hfSoapModel.setRetResultType(retRsType);
				setHandlerEvent(hfSoapModel);
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			retRsType.setResultCode("ERR0002");
			retRsType.setResultDesc(exp.getMessage());
		} finally {
			hfSoapModel.setRetResultType(retRsType);
		}
		return retRsType;
	}

}
