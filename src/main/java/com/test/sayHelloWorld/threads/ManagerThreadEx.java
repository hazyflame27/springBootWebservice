package com.test.sayHelloWorld.threads;

import java.sql.Connection;

import com.fss.thread.ManageableThread;
import com.test.sayHelloWorld.utils.HFSoapConvert;

public abstract class ManagerThreadEx extends ManageableThread {
	public HFSoapConvert hfConvertObject = new HFSoapConvert();

	public ThreadManagerEx getThreadManagerEx() {
		return (ThreadManagerEx) mmgrMain;
	}

	public Connection getConnectionFromPool() throws Exception {
		return this.mmgrMain.getConnection();
	}

	public void delay(int iTime) {
		try {
			Thread.sleep(iTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isReloadConnection(String strError) {
		boolean blIsReload = false;

		try {
			strError = strError.trim();
			if (strError.startsWith("ORA-12154") || strError.startsWith("ORA-12203") || strError.startsWith("ORA-12500")
					|| strError.startsWith("ORA-12545") || strError.startsWith("ORA-12560")) {
				blIsReload = true;
			}
		} catch (Exception exp) {
			blIsReload = false;
		}
		return blIsReload;
	}
}
