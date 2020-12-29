package com.work.controllers;

import com.work.models.ERole;
import com.work.models.Role;
import com.work.models.User;
import com.work.payload.request.UserRequest;
import com.work.payload.response.MessageResponse;
import com.work.payload.response.NotFoundResponse;
import com.work.payload.response.UserResponse;
import com.work.repository.RoleRepository;
import com.work.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers() {
        List<User> retrieve = userRepository.findAll();
        if (retrieve.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Users not found!"));
        }

        List<UserResponse> responseList=new ArrayList<>();
        for (User item: retrieve){
            List<String> roles=new ArrayList<>();
            for(Role roleItem: item.getRoles()){
                roles.add(roleItem.getName().toString());
            }
            UserResponse userResponse = new UserResponse(
                    item.getId(),
                    item.getUsername(),
                    item.getEmail(),
                    roles
            );
            responseList.add(userResponse);
        }

        return ResponseEntity.ok(responseList);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@Valid @RequestParam(name = "user_id") Long user_id) {

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundResponse("Error: User is not found."));

        userRepository.deleteById(user.getId());

        return ResponseEntity.ok().body(new MessageResponse("User deleted successfully!"));
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userRepository.findById(userRequest.getUser_id())
                .orElseThrow(() -> new NotFoundResponse("Error: User is not found."));

        user.setUsername(userRequest.getNew_username());
        user.setEmail(userRequest.getNew_email());
        user.setPassword(encoder.encode(userRequest.getNew_password()));
        Set<Role> roles=new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        if(userRequest.getIsAdmin().equals("true")){
            userRole=roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        }
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok().body(new MessageResponse("User updated successfully!"));
    }
}
