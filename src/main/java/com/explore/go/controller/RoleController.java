package com.explore.go.controller;

import com.explore.go.model.Role;
import com.explore.go.model.User;
import com.explore.go.repository.RoleRepository;
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
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value="CreateRole", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Role role){
        if(Objects.nonNull(role) && role.getRoleKey()>0){
            Role roleByKey = roleRepository.findRoleByRoleKey(role.getRoleKey());
            if(Objects.isNull(roleByKey)){
                Role saveRole = roleRepository.save(role);
                return ResponseEntity.ok(saveRole + " Created successfully");
            }else{
                return ResponseEntity.badRequest().body("Role exists already!");
            }

        }
        return ResponseEntity.ok("");
    }

    @RequestMapping(value="GetAllRoles",method = RequestMethod.GET)
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> roles = roleRepository.findAll();

        return ResponseEntity.ok(roles);
    }

    @RequestMapping(value = "getRoleById", method = RequestMethod.GET)
    public ResponseEntity<Role> getRoleById(@RequestParam Long id){
        Optional<Role> roles = roleRepository.findById(id);

        if(roles.isPresent()){
            Role role = roles.get();
            return ResponseEntity.ok(role);
        }else{
            return ResponseEntity.ok(null);
        }

    }

    @RequestMapping(value = "updateRole",method = RequestMethod.PUT)
    public ResponseEntity updateRole(@RequestBody Role role,@RequestParam Long id){
        AtomicReference<Role> savedRole = new AtomicReference<>(new Role());

        roleRepository.findById(id)
                      .ifPresent(oldRole -> {
                            oldRole.setDescription(role.getDescription());
                            oldRole.setRoleKey(role.getRoleKey());

                            savedRole.set(roleRepository.saveAndFlush(oldRole));

        });

        return ResponseEntity.ok(savedRole);
    }

    @RequestMapping(value = "deleteRoleById",method = RequestMethod.DELETE)
    public void deleteRoleById(@RequestParam Long id){
        roleRepository.deleteById(id);
    }

}
