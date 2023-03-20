package edu.cpp.CYS.service;

import java.util.List;

import edu.cpp.CYS.UserDto;
import edu.cpp.CYS.model.U;

public interface UserS {
    void saveUser(UserDto userDto);

    U findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
