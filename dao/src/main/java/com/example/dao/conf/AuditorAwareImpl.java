package com.example.dao.conf;


import com.example.dao.entity.User;
import com.example.dao.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Creating implementation of AuditorAware and override its methods to provide currently logged in user
 */
@AllArgsConstructor
public class AuditorAwareImpl implements AuditorAware<User> {

    private UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        //TODO
//        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return null;
    }
}
