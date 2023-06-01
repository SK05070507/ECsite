package katachi.spring.exercise.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {

	UserDetails loadUserByUsername(String username);

}
