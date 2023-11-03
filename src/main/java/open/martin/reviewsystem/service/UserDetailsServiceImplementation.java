package open.martin.reviewsystem.service;

import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.entity.UserAccount;
import open.martin.reviewsystem.repository.UserAccountRepository;
import open.martin.reviewsystem.type.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("User with name " + username + " not found")
        );
        return UserDetailsImplementation.build(userAccount);
    }
}
