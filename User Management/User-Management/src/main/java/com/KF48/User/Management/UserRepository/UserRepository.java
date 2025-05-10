package com.KF48.User.Management.UserRepository;

import com.KF48.User.Management.UserModel.Role;
import com.KF48.User.Management.UserModel.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;

//import java.lang.ScopedValue;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find a user by email
    Optional<User> findByEmail(String email);

    // Find users by role
    List<User> findByRole(Role role);

   // ResponseEntity<User> createUser(@RequestBody User user);

    // Custom update query to update user details
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.firstname = :firstname, u.secondname = :secondname, u.email = :email, u.role = :role WHERE u.id = :id")
    int updateUser(@Param("id") Integer id,
                   @Param("firstname") String firstname,
                   @Param("secondname") String secondname,
                   @Param("email") String email,
                   @Param("password") String password,
                   @Param("role") Role role);
}
/*
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    public Optional<User> findByEmail(String email);
    User findByRole(Role role);

    List<User> getAllUsers();

    User updateUser(Integer id, User updatedUser);

    void deleteUser(Integer id);

    User getUserById(Integer id);

}
*/