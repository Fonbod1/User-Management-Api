package com.KF48.User.Management.UserService;

import com.KF48.User.Management.UserModel.User;
import com.KF48.User.Management.UserRepository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
    public class UserService1 {
    @Autowired
    public PasswordEncoder passwordEncoder;
        private final UserRepository userRepository;
         @Autowired
        public UserService1(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

    public User createUser(User user) {
        // Check if the email is already in use
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Encode the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user in the database
        return userRepository.save(user);
    }
       // public User createUser(User user){
          //  return userRepository.save(user);
       // }

       public List<User> getAllUser(){
            return userRepository.findAll();
       }
       public User getUserById(Integer id){
            return userRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("User not found"));
       }
        public User updateUser(Integer id, User updatedUser) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Update user fields
            user.setFirstname(updatedUser.getFirstname());
            user.setSecondname(updatedUser.getSecondname());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());

            //User updated = userRepository.save(user);
            return userRepository.save(user);
        }

        public void deleteUser(Integer id) {
            if (!userRepository.existsById(id)) {
                //return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found
            }
            userRepository.deleteById(id);
           // return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content
        }
    }


