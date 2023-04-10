

package edu.cpp.CYS.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.cpp.CYS.UserRepository;
import edu.cpp.CYS.model.U;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private final HttpServletRequest request;

    public CustomUserDetailsService(UserRepository userRepository, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.request = request;
    }

    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    U user = userRepository.findByUsername(username);

    if (user != null) {
        // create a session for the user
        HttpSession session = request.getSession(true);

        // store some information about the user in the session
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                true, true, true, true,
                    Collections.emptyList());
    } else {
        throw new UsernameNotFoundException("Invalid username or password.");
    }
}

}

