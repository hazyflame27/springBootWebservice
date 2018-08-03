package com.test.sayHelloWorld.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fss.util.StringUtil;
import com.telcordia.inpac.ws.npcdatatypes.MessageHeaderType;
import com.telcordia.inpac.ws.npcdatatypes.NPCMessageType;
import com.test.sayHelloWorld.models.HFDataModel;
import com.test.sayHelloWorld.models.HFSoapModel;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class HFSoapConvert {
	public Object readObject(Class<?> c, JSONObject json) {

		Object objClass = null;

		try {
			Field[] fields = c.getDeclaredFields();
			objClass = c.newInstance();

			if (fields != null) {
				for (Field field : fields) {
					if (field.getType().isAssignableFrom(String.class)) {
						field.setAccessible(true);
						field.set(objClass, json.get(field.getName()));
					} else if (field.getType().isAssignableFrom(byte[].class)) {
						field.setAccessible(true);
						field.set(objClass, ((String) json.get(field.getName())).getBytes());
					} else if (field.getType().isAssignableFrom(ArrayList.class)) {
						List lsData = null;
						JSONArray jsArray = null;

						try {
							jsArray = json.getJSONArray(field.getName());
						} catch (Exception exp) {
							exp.printStackTrace();
						}
						if (jsArray != null) {
							lsData = new ArrayList<>();
							for (int i = 0; i < jsArray.length(); i++) {
								JSONObject js = jsArray.getJSONObject(i);

								ParameterizedType type = (ParameterizedType) field.getGenericType();
								Type t = type.getActualTypeArguments()[0];
								Object objSubClass = readObject(Class.forName(t.getTypeName()), js);

								lsData.add(objSubClass);
							}
						} else {
							Iterator<String> itKey = json.keys();
							lsData = new ArrayList<>();

							while (itKey.hasNext()) {
								JSONArray jsonArray = json.getJSONArray(itKey.next());
								for (int i = 0; i < jsonArray.length(); i++) {
									JSONObject js = jsonArray.getJSONObject(i);

									ParameterizedType type = (ParameterizedType) field.getGenericType();
									Type t = type.getActualTypeArguments()[0];
									Object objSubClass = readObject(Class.forName(t.getTypeName()), js);

									lsData.add(objSubClass);
								}
							}
						}
						field.setAccessible(true);
						field.set(objClass, lsData);
					} else {
						JSONArray jsonArray = (JSONArray) json.get(field.getName());

						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject js = jsonArray.getJSONObject(i);
							Object objSubClass = readObject(field.getType(), js);

							field.setAccessible(true);
							field.set(objClass, objSubClass);
						}
					}
				}
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return objClass;
	}

	public HashMap convertJsonToHashMap(JSONObject json) {
		HashMap hData = new HashMap();

		try {
			Iterator<String> itKeys = json.keys();

			while (itKeys.hasNext()) {
				String strKey = itKeys.next();
				Object o = json.get(strKey);

				if (o instanceof String) {
					hData.put(strKey, o);
				} else if (o instanceof JSONArray) {
					JSONArray jsonArray = (JSONArray) o;
					jsonArray.length();
				}
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return hData;
	}

	public Object getObject(HFSoapModel hfSoapModel) {
		Object objData = new HashMap<>();
		MessageHeaderType messageHeader = hfSoapModel.getNpcDataType() != null
				? hfSoapModel.getNpcDataType().getMessageHeader() : null;

		if (messageHeader != null) {
			String strTransID = StringUtil.nvl(messageHeader.getTransactionID(), "");
			NPCMessageType npcMessage = hfSoapModel.getNpcDataType().getNPCMessage();

			Field[] listField = npcMessage.getClass().getDeclaredFields();

			if (listField != null) {
				for (Field field : listField) {
					XmlElement xmlAnotation = field.getDeclaredAnnotation(XmlElement.class);

					if (xmlAnotation != null) {
						if (!"".equals(strTransID)) {
							try {
								field.setAccessible(true);

								Field fieldValue = hfSoapModel.getNpcDataType().getNPCMessage().getClass()
										.getDeclaredField(field.getName());

								fieldValue.setAccessible(true);
								objData = fieldValue.get(npcMessage);
							} catch (Exception exp) {
								exp.printStackTrace();
							}
						}
					}
				}
			}
		}
		return objData;
	}
	
	public HFDataModel convertSoapToData(HFSoapModel hfSoapModel) {
		HFDataModel data = new HFDataModel();
		data.setWordSay(hfSoapModel.getNpcDataType().getNPCMessage().getWordSay());
		data.setNumSay(Double.parseDouble(hfSoapModel.getNpcDataType().getNPCMessage().getNumberSay()));
		return data;
	}

}
