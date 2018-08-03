package com.test.sayHelloWorld.models;

import java.io.Serializable;

/**
 * The persistent class for the AP_PARAM database table.
 * 
 */
public class KeyValueModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String key;
	private String value;

	public KeyValueModel() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}