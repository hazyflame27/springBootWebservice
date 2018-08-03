package com.test.sayHelloWorld.threads;

import com.fss.queue.LinkQueue;
import com.fss.thread.ThreadConstant;
import com.fss.util.StringUtil;
import com.test.sayHelloWorld.cache.AppManagerCache;
import com.test.sayHelloWorld.models.HFDataModel;
import com.test.sayHelloWorld.models.HFSoapModel;

public class HFRequestThread extends ManagerThreadEx {

	private LinkQueue<HFSoapModel> mlqHFRequestQueue = null;

	@SuppressWarnings("unchecked")
	@Override
	protected void beforeSession() throws Exception {
		super.beforeSession();
		this.miDelayTime = loadInteger("DelayTime");
		this.mlqHFRequestQueue = getThreadManagerEx().getHFRequestQueue();
	}

	@Override
	protected void processSession() throws Exception {
		while (this.miThreadCommand != ThreadConstant.THREAD_STOP) {
			HFSoapModel hfSoapModel = null;
			try {
				hfSoapModel = this.mlqHFRequestQueue.dequeueWait(this.miDelayTime);
				if (hfSoapModel != null) {
					Object objData = this.hfConvertObject.getObject(hfSoapModel);

					if (objData != null) {
						logMonitor("Begin: ");
						HFDataModel hfDataModel = this.hfConvertObject.convertSoapToData(hfSoapModel);
						String sayWord = StringUtil.nvl(hfDataModel.getWordSay(),"");
						if (sayWord != null && !"".equals(sayWord)) {
							logMonitor(sayWord);
						} else {
							logMonitor(StringUtil.nvl(AppManagerCache.mhErrorMessageCache.get("ERROR0000"),""));
						}
					}
				}
			} catch (Exception exp) {
				exp.printStackTrace();
				logMonitor("Error: " + exp.getMessage());
			} finally {
				logMonitor("End. ");
			}
		}
	}

	@Override
	protected void afterSession() throws Exception {
		super.afterSession();
		this.mlqHFRequestQueue = null;
	}

}
