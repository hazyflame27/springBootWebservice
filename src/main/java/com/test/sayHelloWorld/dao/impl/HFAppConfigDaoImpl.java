package com.test.sayHelloWorld.dao.impl;

import java.util.concurrent.ConcurrentHashMap;

import com.test.sayHelloWorld.abstracts.HFManagerConnectionBase;
import com.test.sayHelloWorld.dao.itface.HFAppConfigDao;
import com.test.sayHelloWorld.models.KeyValueModel;

public class HFAppConfigDaoImpl extends HFManagerConnectionBase implements HFAppConfigDao {

	@Override
	public ConcurrentHashMap<String, KeyValueModel> loadAllByType(String strType, int iFetchSize) {
//		String strSQL = "SELECT  FROM  WHERE ";
		ConcurrentHashMap<String, KeyValueModel> hData = new ConcurrentHashMap<>();

//		ResultSet rs = null;
//		PreparedStatement pstm = null;

		try {
//			pstm = getConnection().prepareStatement(strSQL);
//			rs = pstm.executeQuery();
//			rs.setFetchSize(iFetchSize);
//			while (rs.next()) {
//
//			}
			
			KeyValueModel keyValueModel = new KeyValueModel();
			keyValueModel.setKey("KEY");
			keyValueModel.setValue("VALUE");
			hData.put(keyValueModel.getKey(), keyValueModel);
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
//			Database.closeObject(rs);
//			Database.closeObject(pstm);
		}
		return hData;
	}

}
