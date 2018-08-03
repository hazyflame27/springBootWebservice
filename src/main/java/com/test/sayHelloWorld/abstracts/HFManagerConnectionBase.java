package com.test.sayHelloWorld.abstracts;

import java.sql.Connection;

public abstract class HFManagerConnectionBase {
	private Connection conn = null;
	private String mstrExpMessage = "";

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public Connection getConnection() {
		return this.conn;
	}

	public String getExpMessage() {
		return this.mstrExpMessage;
	}

}
