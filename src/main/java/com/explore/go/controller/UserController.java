package com.explore.go.controller;


import com.explore.go.model.Role;
import com.explore.go.model.User;
import com.explore.go.repository.RoleRepository;
import com.explore.go.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@CrossOrigin
@RestController
@EntityScan(value="com.explore.go.model")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value="CreateUser", method = RequestMethod.POST)
    public ResponseEntity  create(@RequestBody User user){
        if(Objects.nonNull(user) && Objects.nonNull(user.getRole()) && !user.getEmailAddress().isEmpty()){
            Long roleKey = user.getRole()
                               .stream()
                               .findFirst()
                               .get()
                               .getRoleKey();
            Role role = roleRepository.findRoleByRoleKey(roleKey);
            User userValid = userRepository.findUserByEmailAddress(user.getEmailAddress());


            if(Objects.nonNull(userValid)){
                return ResponseEntity.badRequest().body("User Exists Already!");
            }

            if(Objects.nonNull(role)){
                User saUser = userRepository.save(user);
                return ResponseEntity.ok("User successfully created!");
            }else{
                return ResponseEntity.badRequest().body("User Role not Valid!");
            }

        }else{
            return ResponseEntity.badRequest().body("User Role not Valid!");
        }
    }

    @RequestMapping(value="GetAllUser",method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }

    @RequestMapping(value = "getUserById", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@RequestParam Long id){
        Optional<User> users = userRepository.findById(id);

        if(users.isPresent()){
            User user = users.get();
            return ResponseEntity.ok(user);
        }else{
          return ResponseEntity.ok(null);
        }

    }

    @RequestMapping(value = "updateUser",method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody User user,@RequestParam Long id){
        AtomicReference<User> savedUser = new AtomicReference<>(new User());

        userRepository.findById(id).ifPresent(oldUser -> {
             oldUser.setIdNumber(user.getIdNumber());
             oldUser.setName(user.getName());
             oldUser.setSurname(user.getSurname());

            savedUser.set(userRepository.saveAndFlush(oldUser));

        });

        return ResponseEntity.ok(savedUser);
    }

    @RequestMapping(value = "deleteUserById",method = RequestMethod.DELETE)
    public void deleteUserById(@RequestParam Long id){
        userRepository.deleteById(id);
    }
}
