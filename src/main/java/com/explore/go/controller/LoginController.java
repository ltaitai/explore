package com.explore.go.controller;

import com.explore.go.model.User;
import com.explore.go.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin
@RestController
@EntityScan(value="com.explore.go.model")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "authenticate",method = RequestMethod.GET)
    public ResponseEntity<User> authenticate(@RequestParam String username, @RequestParam String password){
        User user = userRepository.findUserByEmailAddressAndPassword(username,password);
        if(Objects.nonNull(user)){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.ok(null);
        }
    }
}
