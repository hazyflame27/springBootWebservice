package com.test.sayHelloWorld.cache;

import java.sql.Connection;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import com.fss.dictionary.Dictionary;
import com.fss.dictionary.DictionaryNode;
import com.fss.sql.Database;
import com.test.sayHelloWorld.business.impl.HFAppConfigManagerImpl;
import com.test.sayHelloWorld.models.KeyValueModel;
import com.test.sayHelloWorld.utils.SoapDefineUtils;

@SuppressWarnings({"unchecked","rawtypes"})
public class AppManagerCache {
	public static ConcurrentHashMap<String, KeyValueModel> mhAppParamCache = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<String, String> mhErrorMessageCache = new ConcurrentHashMap<>();
	
	public static void initCache(Connection conn) {
		mhAppParamCache.clear();
		mhErrorMessageCache.clear();
		
		HFAppConfigManagerImpl hfAppConfigManagerImpl = new HFAppConfigManagerImpl();
		hfAppConfigManagerImpl.setConnection(conn);
		mhAppParamCache = hfAppConfigManagerImpl.loadAllByType(SoapDefineUtils.PARAM_TYPE, 300);
		Database.closeObject(conn);

		try {
			Dictionary dicError = new Dictionary("conf/Error.properties");
			Vector<DictionaryNode> vtDicNode = dicError.getChildList();

			if (vtDicNode != null) {
				for (DictionaryNode dicNode : vtDicNode) {
					mhErrorMessageCache.put(dicNode.mstrName, dicNode.mstrValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getAppParam(String strKey) {
		int iValue = 0;
		try {
			iValue = Integer.parseInt(mhAppParamCache.get(strKey).getValue());
		} catch (Exception exp) {

		}
		return iValue;
	}

}
