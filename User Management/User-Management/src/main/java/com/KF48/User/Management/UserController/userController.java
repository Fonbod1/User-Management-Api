package com.KF48.User.Management.UserController;

import com.KF48.User.Management.UserModel.User;

import com.KF48.User.Management.UserService.UserService1;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "USER ENDPOINT", description = "This endpoint is used to manage users")
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class userController {
    private final UserService1 userService1;

    //@GetMapping("/hello")
    @RequestMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("You are now logged in as a User");
    }


    // View all users
    @PreAuthorize("isAuthenticated()")

    @GetMapping("/get/all")
    @Tag(name = "VIEW ALL USERS", description = "This endpoint is used to view all users in the database")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService1.getAllUser());
    }

    // Get a user by ID
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get/{id}")
    @Tag(name = "GET A USER BY ID", description = "This endpoint is used to get a user by their ID")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService1.getUserById(id));
    }

    // Update user
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/update/{id}")
    @Tag(name = "UPDATE A PARTICULAR USER", description = "This endpoint is used to update a particular user")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(userService1.updateUser(id, user));
    }

    // Delete user
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{id}")
    @Tag(name = "DELETE USER", description = "This endpoint is used to delete a particular user")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService1.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Create a new user
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    @Tag(name = "CREATE A NEW USER", description = "This endpoint is used to create a new user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService1.createUser(user));
    }

}
