package com.work.controllers;

import com.work.models.User;
import com.work.payload.request.TodoUpdateRequest;
import com.work.payload.request.UserRequest;
import com.work.payload.response.MessageResponse;
import com.work.payload.response.NotFoundResponse;
import com.work.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers() {
        List<User> retrieve = userRepository.findAll();
        if (retrieve.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Users not found!"));
        }

        return ResponseEntity.ok(retrieve);
    }

    @DeleteMapping("/deleteUsers")
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
        userRepository.save(user);

        return ResponseEntity.ok().body(new MessageResponse("User updated successfully!"));
    }
}
