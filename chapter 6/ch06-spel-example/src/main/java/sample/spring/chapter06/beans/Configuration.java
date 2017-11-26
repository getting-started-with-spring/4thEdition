package sample.spring.chapter06.beans;

public class Configuration {
	public static String environment = "DEV";

	public String getCountry() {
		return "Some country";
	}

	public String getState() {
		return "Some state";
	}
}
