package application;

import application.service.MinecraftUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration()
public class SpringBeansCustomBeansConfig extends WebMvcConfigurerAdapter{

	
	@Bean
	AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(accountDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
	}
	
	@Bean(name="CustomAccountDetailService")
	public UserDetailsService accountDetailService() {
		return new MinecraftUserDetailService();
	}

	@Bean
	Object passwordEncoder() {
		return new Md5PasswordEncoder();
	}
	


}
