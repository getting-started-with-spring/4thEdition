package sample.spring.chapter06.beans;

public class Sample {
	private String environment;
	private String currency;
	private String country;
	private String state;

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Sample [environment=" + environment + ", currency=" + currency
				+ ", country=" + country + ", state=" + state + "]";
	}
}