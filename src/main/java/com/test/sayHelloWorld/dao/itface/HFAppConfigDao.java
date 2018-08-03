package com.test.sayHelloWorld.dao.itface;

import java.util.concurrent.ConcurrentHashMap;

import com.test.sayHelloWorld.models.KeyValueModel;

public interface HFAppConfigDao {
	public ConcurrentHashMap<String,KeyValueModel> loadAllByType(String strType, int iFetchSize);
	
}
