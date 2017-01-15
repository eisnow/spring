package eisnow.core;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author liqiang
 *
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

//    @Autowired
//    private UserRepository userRepository;

	public CustomUserDetailsService() {
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null; // TODO: userRepository.findByUsernameWithAuthorities(username);
	}
}