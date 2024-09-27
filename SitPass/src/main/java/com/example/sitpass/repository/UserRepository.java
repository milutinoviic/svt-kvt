package com.example.sitpass.repository;

import com.example.sitpass.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {



    public User findUserByEmail(String email);

    public boolean existsUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id NOT IN (SELECT m.user.id FROM Manages m)")
    List<User> findUsersNotInManages();

}
