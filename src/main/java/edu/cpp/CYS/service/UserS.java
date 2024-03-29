package edu.cpp.CYS.service;

import java.util.List;

import edu.cpp.CYS.UserDto;
import edu.cpp.CYS.model.U;

public interface UserS {
    void saveUser(UserDto userDto);
    void saveUser(U user);

    U findUserByUsername(String username);

    List<UserDto> findAllUsers();

    public List<U> searchUsers(String query);
    public void follow(U currentUser, U targetUser);
    public void unfollow(U currentUser, U targetUser);


}
