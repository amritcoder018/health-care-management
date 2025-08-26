package com.amritsolution.service;

import com.amritsolution.model.db.AppUser;
import com.amritsolution.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("into loading of Username password");
        Optional<AppUser> userOp;
        if(username.length()>10)
            userOp=userRepository.findByUsername(username);
        else
            userOp=userRepository.findByMobileNo(username);

        AppUser user=userOp
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new CustomUserDetails(user.getUsername(),user.getRoles(),user.getPassword());
    }
}