package toiletgo.user.auth;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import toiletgo.user.entity.User;
import toiletgo.user.repository.UserRepository;
import org.springframework.security.core.userdetails.User.*;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        UserBuilder userBuilder = null;

        if (user.isPresent()) {
            User currentUser = user.get();
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.password(currentUser.getPassword());
            // userBuilder.roles(currentUser.getRole());
        } else {
            throw new UsernameNotFoundException("404");
        }

        return userBuilder.build();
    }
}
