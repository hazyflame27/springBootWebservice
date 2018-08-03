package com.test.sayHelloWorld.business.impl;

import java.sql.Connection;
import java.util.concurrent.ConcurrentHashMap;

import com.test.sayHelloWorld.abstracts.HFManagerConnectionBase;
import com.test.sayHelloWorld.business.itface.HFAppConfigManager;
import com.test.sayHelloWorld.dao.impl.HFAppConfigDaoImpl;
import com.test.sayHelloWorld.models.KeyValueModel;


public class HFAppConfigManagerImpl extends HFManagerConnectionBase implements HFAppConfigManager {
	private HFAppConfigDaoImpl hfAppConfigDaoImpl = null;

	@Override
	public void setConnection(Connection conn) {
		this.hfAppConfigDaoImpl = new HFAppConfigDaoImpl();
		this.hfAppConfigDaoImpl.setConnection(conn);
	}

	@Override
	public ConcurrentHashMap<String, KeyValueModel> loadAllByType(String strType, int iFetchSize) {
		return this.hfAppConfigDaoImpl.loadAllByType(strType, iFetchSize);
	}

}
