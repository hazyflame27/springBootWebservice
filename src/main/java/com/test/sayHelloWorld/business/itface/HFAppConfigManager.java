package com.test.sayHelloWorld.business.itface;

import java.util.concurrent.ConcurrentHashMap;

import com.test.sayHelloWorld.models.KeyValueModel;

public interface HFAppConfigManager {
	
	public ConcurrentHashMap<String, KeyValueModel> loadAllByType(String strType, int iFetchSize);
}
