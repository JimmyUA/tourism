package application;

import application.persistence.repository.UserRepository;
import application.service.TourismUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration()
public class SpringBeansCustomBeansConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private UserRepository userRepository;

	@Bean
	AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(accountDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
	}
	
	@Bean(name="CustomAccountDetailService")
	public UserDetailsService accountDetailService() {
		return new TourismUserDetailService(userRepository);
	}

	@Bean
	Object passwordEncoder() {
		return new Md5PasswordEncoder();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
