package eisnow.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author liqiang
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider authenticationProvider() {
		AuthenticationProvider authenticationProvider = new CustomAuthenticationProvider(userDetailsService);
		return authenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 暂时使用基于内存的AuthenticationProvider
		// auth.inMemoryAuthentication().withUser("username").password("password").roles("USER");
		// 自定义AuthenticationProvider
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**", "/kaptcha");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 暂时禁用csrf,并自定义登录页和登出URL
		http.addFilterBefore(new KaptchaAuthenticationFilter("/login", "/login?error"),
				UsernamePasswordAuthenticationFilter.class).csrf().disable().authorizeRequests()
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").failureUrl("/login?error")
				.usernameParameter("username").passwordParameter("password").permitAll().and().logout()
				.logoutUrl("/logout").permitAll();
	}
}