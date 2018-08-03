package com.test.sayHelloWorld.service.events;

import java.lang.reflect.Field;

import com.fss.dictionary.Dictionary;
import com.telcordia.inpac.ws.npcdatatypes.RetResultType;
import com.test.sayHelloWorld.command.messages.RetResultMessageCode;
import com.test.sayHelloWorld.main.HFApplicationMain;
import com.test.sayHelloWorld.models.HFSoapModel;
import com.test.sayHelloWorld.service.interfaces.HFValidateInput;
import com.test.sayHelloWorld.utils.HFSoapConvert;

public class HFValidateListenerClass implements HFValidateInput {
	private Dictionary mdValidate = HFApplicationMain.getValidateDic();
	private HFSoapConvert hfConvertObject = new HFSoapConvert();

	@SuppressWarnings("unused")
	@Override
	public RetResultType validateInput(HFSoapModel hfSoapModel) {
		Object objData = this.hfConvertObject.getObject(hfSoapModel);
		RetResultType retResultType = this.validateObject(objData);
		return retResultType;
	}

	@SuppressWarnings("unused")
	private RetResultType validateObject(Object objData) {
		String strComment = "";
		RetResultType retResultType = new RetResultType();

		try {
			if (objData != null) {
				Field[] listFile = objData.getClass().getDeclaredFields();
				boolean blValidate = false;

				// validate o day
				if (true) {
					blValidate = true;
				}

				if (blValidate) {
					retResultType.setResultCode(RetResultMessageCode.SUCCESSED_STATUS);
					retResultType.setResultDesc(RetResultMessageCode.SUCCESSED_MESSAGE);
				}

			} else {
				retResultType.setResultCode(RetResultMessageCode.INVALID_DATA);
				retResultType.setResultDesc(RetResultMessageCode.INVALID_DATA);
			}
		} catch (

		Exception exp) {
			exp.printStackTrace();
		}
		return retResultType;
	}

}
