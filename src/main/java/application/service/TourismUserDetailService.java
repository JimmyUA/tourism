package application.service;

import application.entity.AppUser;
import application.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class TourismUserDetailService implements UserDetailsService{


	private UserRepository userRepository;

	public TourismUserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



		AppUser user = userRepository.findUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), Collections.emptyList());
	}

}
