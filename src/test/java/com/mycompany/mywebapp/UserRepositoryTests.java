package com.mycompany.mywebapp;

import com.mycompany.mywebapp.user.User;
import com.mycompany.mywebapp.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("test123111@test.com");
        user.setPassword("password111123");
        user.setFirstName("abc11111");
        user.setLastName("def111111");

        User savedUser = repo.save(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testUpdate(){
        Integer userId = 1;
        Optional<User> userOptional = repo.findById(userId);
        User user = userOptional.get();
        user.setPassword("newPassword");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        assertThat(updatedUser.getPassword()).isEqualTo("newPassword");

    }
    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        assertThat(users).isNotNull();

        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testGet(){
        Integer userId = 1;
        Optional<User> res = repo.findById(userId);
        assertThat(res).isNotNull();
        System.out.println(res.get());
    }

    @Test
    public void testDelete(){
        Integer userId = 4;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        assertThat(optionalUser).isNotPresent();
        System.out.println("Deleted user successfully");
    }

}
