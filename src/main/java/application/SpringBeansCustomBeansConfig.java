package application;

import application.entity.AppUser;
import application.persistence.repository.UserRepository;
import application.service.TourismUserDetailService;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration()
public class SpringBeansCustomBeansConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private UserRepository userRepository;

	@Bean(name="CustomAccountDetailService")
	public UserDetailsService accountDetailService() {
		AppUser admin = new AppUser();
		admin.setUsername("admin");
		admin.setPassword(bCryptPasswordEncoder().encode("admin"));
		admin.setEmail("admin@admin");

		userRepository.save(admin);

		return new TourismUserDetailService(userRepository);
	}


	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}


}
