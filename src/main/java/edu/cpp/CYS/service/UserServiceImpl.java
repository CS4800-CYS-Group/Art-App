package edu.cpp.CYS.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.cpp.CYS.RoleRepository;
import edu.cpp.CYS.UserDto;
import edu.cpp.CYS.UserRepository;
import edu.cpp.CYS.model.Role;
import edu.cpp.CYS.model.U;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserS {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        U user = new U();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public U findUserByEmail(String email) {
        return userRepository.findByEmail(email);
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
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}