package sample.spring.chapter05.domain;

public class DataSource {
	private String url;
	private String username;
	private String password;
	private String driverClass;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	@Override
	public String toString() {
		return "DataSource [url=" + url + ", username=" + username
				+ ", password=" + password + ", driverClass=" + driverClass
				+ "]";
	}
}