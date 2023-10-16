package com.loviselliandrea.SpringBootProject.dao;

import com.loviselliandrea.SpringBootProject.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*1:27 lez 3*/
@Repository("dbUserDAO")
public interface UserRepositoryDAO extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
    public List<User> findByUsernameContains(String partialUsername);
    public List<User> findByUsernameContainsAndEmailContains(String partialUsername, String partialMail);
}
