package com.test.sayHelloWorld.service.events;

import com.fss.queue.LinkQueue;
import com.test.sayHelloWorld.main.HFApplicationMain;
import com.test.sayHelloWorld.models.HFSoapModel;
import com.test.sayHelloWorld.service.interfaces.HFEventListener;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class HFEventListenerImpl implements HFEventListener {
	private LinkQueue mlqRequestQueue = HFApplicationMain.threadManagerEx.getHFRequestQueue();

	@Override
	public void handlerEvent(HFSoapModel hfSoapModel) {
		this.mlqRequestQueue.enqueueNotify(hfSoapModel);
	}

}
