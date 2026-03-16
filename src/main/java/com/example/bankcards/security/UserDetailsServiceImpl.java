package com.example.bankcards.security;

import com.example.bankcards.entity.User;
import com.example.bankcards.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;

    public UserDetailsServiceImpl(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Пользователь не найден: " + username));
    }
}
