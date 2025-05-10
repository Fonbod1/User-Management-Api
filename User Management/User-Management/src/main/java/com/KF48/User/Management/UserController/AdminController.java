package com.KF48.User.Management.UserController;

import com.KF48.User.Management.UserModel.User;
import com.KF48.User.Management.UserService.UserService1;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
      @Autowired
    public  final UserService1 userService1;
    @RequestMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Welcome You are now login as an administrator");
    }


    // View all users
    @GetMapping("/get/all")
    @Tag(name = "VIEW ALL USERS", description = "This endpoint is used to view all users in the database")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService1.getAllUser());
    }

    // Get a user by ID
    @GetMapping("/get/{id}")
    @Tag(name = "GET A USER BY ID", description = "This endpoint is used to get a user by their ID")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService1.getUserById(id));
    }

    // Update user
    @PutMapping("/update/{id}")
    @Tag(name = "UPDATE A PARTICULAR USER", description = "This endpoint is used to update a particular user")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(userService1.updateUser(id, user));
    }

    // Delete user
    @DeleteMapping("/delete/{id}")
    @Tag(name = "DELETE USER", description = "This endpoint is used to delete a particular user")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService1.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Create a new user
    @PostMapping("/create")
    @Tag(name = "CREATE A NEW USER", description = "This endpoint is used to create a new user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService1.createUser(user));
    }



}
