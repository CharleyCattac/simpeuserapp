package com.itransition.lobach.simpleuserapp.service;

import com.itransition.lobach.simpleuserapp.builder.UserBuilder;
import com.itransition.lobach.simpleuserapp.domain.Role;
import com.itransition.lobach.simpleuserapp.domain.User;
import com.itransition.lobach.simpleuserapp.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(@NonNull String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user));
        } else {
            throw new UsernameNotFoundException("user " + s +" not found");
        }
    }

    public Optional<User> findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return Optional.empty();
        } else {
            return Optional.of(user);
        }
    }

    public User saveUser(String name, String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getUserRole());

            User newUser = UserBuilder.buildUser(
                    name,
                    username,
                    passwordEncoder.encode(password),
                    roles,
                    System.currentTimeMillis());

            return userRepository.save(newUser);
        }
        return null;
    }

    public void blockUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setBlocked(true);
            userRepository.save(user);
        }
    }

    public void updateLastCheckoutTime(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setMillisWhenLastLogin(System.currentTimeMillis());
            userRepository.save(user);
        }
    }

    public void unblockUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setBlocked(false);
            userRepository.save(user);
        }
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    private Set<GrantedAuthority> getAuthorities(User user) {
        return user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole()))
                .collect(Collectors.toSet());
    }
}
