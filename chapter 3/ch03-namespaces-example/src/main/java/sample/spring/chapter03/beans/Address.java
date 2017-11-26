package sample.spring.chapter03.beans;

import java.util.Map;

@SuppressWarnings("rawtypes")
public class Address {
	private Map addresses;

	public Map getAddresses() {
		return addresses;
	}

	public void setAddresses(Map addresses) {
		this.addresses = addresses;
	}

}
