package sample.spring.chapter16;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebRequestSecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**")
				.hasAnyAuthority("ROLE_CUSTOMER", "ROLE_ADMIN").and()
				.formLogin().and().logout().and().rememberMe().and().headers()
				.cacheControl().and().xssProtection();
	}

	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password("$2a$10$GL3WYjTbs5zlsAQrZU8BlOiKfOXaUulHA.G9.lk5/OA6EHuYQuvty")
				.authorities("ROLE_ADMIN").and().withUser("$2a$10$xlmAHNLJXuZ1cdDloeqXCubD2Nsnw0k206hqccZ1gh1.VTCD2Scw2")
				.password("cust1").authorities("ROLE_CUSTOMER").and()
				.withUser("cust2").password("$2a$10$dcsvRkVmeQXig1UBiXdpS.X5N5osA/XPyflAatK5Oac2dfPrTTDOW")
				.authorities("ROLE_CUSTOMER");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
