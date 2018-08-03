package com.test.sayHelloWorld.threads;

import org.springframework.context.ConfigurableApplicationContext;

import com.fss.queue.LinkQueue;
import com.fss.thread.ProcessorListener;
import com.fss.thread.ThreadManager;
import com.test.sayHelloWorld.cache.AppManagerCache;

@SuppressWarnings("rawtypes")
public class ThreadManagerEx extends ThreadManager {
	private LinkQueue mlqHFRequestQueue = new LinkQueue<>();
	private ConfigurableApplicationContext springApp = null;

	public ThreadManagerEx(int iPort, ProcessorListener lsn) throws Exception {
		super(iPort, lsn);
		loadCache();
	}

	private void loadCache() throws Exception {
		AppManagerCache.initCache(getConnection());
	}

	public void setSpringApp(ConfigurableApplicationContext springApp) {
		this.springApp = springApp;
	}

	public ConfigurableApplicationContext getSpringApp() {
		return this.springApp;
	}

	public LinkQueue getHFRequestQueue() {
		return this.mlqHFRequestQueue;
	}

}
