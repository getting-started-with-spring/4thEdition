package sample.spring.chapter07.bankapp.domain;

public class DataSource {
	private String driverClass;
	private String url;
	private String username;
	private String password;

	public DataSource(String driverClass, String url, String username, String password) {
		this.driverClass = driverClass;
		this.url = url;
		this.username = username;
		this.password = password;
		System.out.println(driverClass + " " + url);
	}

	public String getDriverClass() {
		return driverClass;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
