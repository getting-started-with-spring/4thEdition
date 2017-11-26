package sample.spring.chapter06.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "sample")
public class Sample {
	@Value("#{configuration.environment}")
	private String environment;

	@Value("Some currency")
	private String currency;

	@Value("#{configuration.getCountry()}")
	private String country;

	@Value("#{configuration.state}")
	private String state;

	private String[] splitName;
	private String city;

	@Value("#{configuration}") // -- bean injection
	private Configuration myConfiguration;

	@Value("#{101 > 100}")
	private boolean isGreaterThan;

	@Value("#{3 > 2 && 4 > 3}")
	private boolean isConditionTrue;

	@Value("#{100 + 200 - 300*1 + 4/2}")
	private int totalAmount;

	@Value("#{configuration.environment == 'DEV' ? 'HTTP' : 'HTTPS'}")
	private String httpOrHttps;

	@Value("#{listType[0]}")
	private String listItem;

	@Value("#{mapType['map key 1']}")
	private String mapItem;

	@Value("#{('abcd@xyz.com' matches '^[A-Za-z0-9+_.-]+@(.+)$') == true ? true : false}")
	private boolean isEmailId;

	@Autowired
	public void splitName(@Value("#{configuration.splitName('FirstName LastName')}") String[] splitName) {
		this.splitName = splitName;
	}

	@Autowired
	@Value("#{configuration.getCity()}")
	public void setCity(String city) {
		this.city = city;
	}

	public Configuration getMyConfigurationBean() {
		return myConfiguration;
	}

	public boolean isGreaterThan() {
		return isGreaterThan;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public String getHttpOrHttps() {
		return httpOrHttps;
	}

	public String getListItem() {
		return listItem;
	}

	public String getMapItem() {
		return mapItem;
	}

	public boolean isEmailId() {
		return isEmailId;
	}

	public boolean isConditionTrue() {
		return isConditionTrue;
	}

	public String getEnvironment() {
		return environment;
	}

	public String getCurrency() {
		return currency;
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public String[] getSplitName() {
		return splitName;
	}

	public String getCity() {
		return city;
	}
}