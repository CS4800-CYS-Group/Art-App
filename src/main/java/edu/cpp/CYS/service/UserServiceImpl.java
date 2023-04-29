package edu.cpp.CYS.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.cpp.CYS.UserDto;
import edu.cpp.CYS.UserRepository;
import edu.cpp.CYS.model.U;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserS {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        U user = new U();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
    }

    @Override
    public void saveUser(U user){
        userRepository.save(user);
    }

    @Override
    public U findUserByUsername(String username) {
        U user = userRepository.findByUsername(username);
        return user;
    }

    public List<U> searchUsers(String query) {
        return userRepository.findByUsernameContainingIgnoreCase(query);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<U> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(U user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        return userDto;
    }  
    
    public void follow(U currentUser, U targetUser) {
        currentUser.getFollowing().add(targetUser);
        targetUser.getFollowers().add(currentUser);
        userRepository.save(currentUser);
        userRepository.save(targetUser);
      }
      
      public void unfollow(U currentUser, U targetUser) {
        currentUser.getFollowing().remove(targetUser);
        targetUser.getFollowers().remove(currentUser);
        userRepository.save(currentUser);
        userRepository.save(targetUser);
      }

      public List<U> getFollowingList(U currentUser) {
  return currentUser.getFollowing();
}
}