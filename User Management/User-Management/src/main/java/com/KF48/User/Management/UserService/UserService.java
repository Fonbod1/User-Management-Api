package com.KF48.User.Management.UserService;

import com.KF48.User.Management.UserModel.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;
public interface UserService {
    UserDetailsService userDetailsService();
    UserDetails loadUserByEmail(String email);
    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);


    List<User> getAllUsers();

    User updateUser(Integer id, User updatedUser);

    void deleteUser(Integer id);

    User getUserById(Integer id);


}
