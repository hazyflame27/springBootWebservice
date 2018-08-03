package com.test.sayHelloWorld.threads;

import java.util.Date;

import com.fss.thread.ThreadConstant;
import com.test.sayHelloWorld.server.HFSoapServer;

public class SoapServerThread extends ManagerThreadEx {
	private HFSoapServer soapServer = null;

	@Override
	protected void beforeSession() throws Exception {
		super.beforeSession();
		this.miDelayTime = loadInteger("DelayTime");
		this.soapServer = new HFSoapServer();

		logMonitor("Starting webservice HF");
		this.soapServer.start();
		getThreadManagerEx().setSpringApp(this.soapServer.getSpringApp());
	}

	@Override
	protected void processSession() throws Exception {
		logMonitor("Webservice HF is started at " + new Date().toString());
		while (this.miThreadCommand != ThreadConstant.THREAD_STOP) {
			if (!this.soapServer.isRunning()) {
				logMonitor("Server is topped at " + new Date().toString());
				break;
			} else {
				logMonitor("Webservice HF is alive");
			}

			for (int i = 0; i < this.miDelayTime && this.miThreadCommand != ThreadConstant.THREAD_STOP; i++) {
				delay(1000);
			}
		}
	}

	@Override
	protected void afterSession() throws Exception {
		super.afterSession();
		if (this.soapServer != null) {
			this.soapServer.stop();
			this.soapServer = null;
		}
	}
}
