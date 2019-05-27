package com.zubale.models.dao;


import com.zubale.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    @Query("SELECT u from User u where u.username=?1")
    public User findByUsernameQuery(String username);
}
