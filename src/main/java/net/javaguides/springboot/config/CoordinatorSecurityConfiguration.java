package net.javaguides.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.javaguides.springboot.service.CoordinatorService;

@Configuration
@EnableWebSecurity
@Order(2)
//coordinator security configuration class for login and logout
public class CoordinatorSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Lazy
	private CoordinatorService coordinatorService;

	@Bean
	@Lazy
    public BCryptPasswordEncoder passwordEncoder1() {
        return new BCryptPasswordEncoder();
    }

	@Bean
    public DaoAuthenticationProvider authenticationProvider1() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(coordinatorService);
        auth.setPasswordEncoder(passwordEncoder1());
        return auth;
    }
	@Override
    protected void configure(AuthenticationManagerBuilder auth1) throws Exception {
        auth1.authenticationProvider(authenticationProvider1());
    }
	@Override
	protected void configure(HttpSecurity http1) throws Exception {
		http1.authorizeRequests().anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/coordinator")
				.permitAll()
				.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/coordinator?logout")
				.permitAll();
	}

}
