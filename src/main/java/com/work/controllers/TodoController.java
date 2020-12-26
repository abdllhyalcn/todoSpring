package com.work.controllers;

import com.work.models.*;
import com.work.payload.request.TodoRequest;
import com.work.payload.request.TodoUpdateRequest;
import com.work.payload.response.MessageResponse;
import com.work.payload.response.NotFoundResponse;
import com.work.repository.StatusRepository;
import com.work.repository.TodoRepository;
import com.work.repository.UserRepository;
import com.work.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    TodoRepository todoRepository;

    private UserDetailsImpl getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return (UserDetailsImpl) auth.getPrincipal();
        }
        throw new NotFoundResponse("Error: User is not found.");
    }

    @PostMapping("addTodo")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addTodo(@Valid @RequestBody TodoRequest todoRequest) {
        UserDetailsImpl userDetails = getCurrentUser();

        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new NotFoundResponse("Error: User is not found."));

        Status status;
        if (todoRequest.getStatus() != 0) {
            status = statusRepository.findById(todoRequest.getStatus())
                    .orElseThrow(() -> new NotFoundResponse("Error: Status is not found."));
        } else {
            status = statusRepository.findByName(EStatus.BEKLEMEDE)
                    .orElseThrow(() -> new NotFoundResponse("Error: Default status can not be assigned."));
        }

        Todo todo = new Todo(user, todoRequest.getDescription(), todoRequest.getDate_todo(), status);

        todoRepository.save(todo);

        return ResponseEntity.ok(new MessageResponse("Todo added successfully!"));
    }

    @GetMapping("getTodos")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getTodos() {
        UserDetailsImpl userDetails = getCurrentUser();
        User user;
        if (userDetails != null) {
            user = userRepository.findById(userDetails.getId())
                    .orElseThrow(() -> new NotFoundResponse("Error: User is not found."));
        } else {
            throw new NotFoundResponse("Error: User is not found.");
        }
        List<Todo> todos = user.getTodo();
        if (todos.isEmpty()) {
            throw new NotFoundResponse("Error: Todos are empty.");
        }

        return ResponseEntity.ok().body(todos);

    }

    @GetMapping("getUserTodos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUserTodos(@Valid @RequestParam
            (name = "user=id") Long user_id) {

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundResponse("Error: User is not found."));

        List<Todo> todos = user.getTodo();
        if (todos.isEmpty()) {
            throw new NotFoundResponse("Error: Todos are empty.");
        }

        return ResponseEntity.ok().body(todos);
    }

    @PostMapping("updateTodo")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateTodo(@Valid @RequestBody TodoUpdateRequest todoUpdateRequest) {
        UserDetailsImpl userDetails = getCurrentUser();

        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new NotFoundResponse("Error: User is not found."));

        Todo todo= todoRepository.findById(todoUpdateRequest.getTodo_id())
                .orElseThrow(() -> new NotFoundResponse("Error: Todo is not found."));

        if(todo.getUser()!=user){
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageResponse("User unauthorized to update the Entity!"));
        }

        Status status = statusRepository.findById(todoUpdateRequest.getStatus())
                .orElseThrow(() -> new NotFoundResponse("Error: Status is not found."));

        todo.setDescription(todoUpdateRequest.getDescription());
        todo.setStatus(status);
        todo.setDate_todo(todoUpdateRequest.getDate_todo());
        todoRepository.save(todo);

        return ResponseEntity.ok().body(new MessageResponse("Todo updated successfully!"));
    }

    @DeleteMapping("deleteTodo")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteTodo(@Valid @RequestParam(name = "todo_id") Long todo_id) {
        UserDetailsImpl userDetails = getCurrentUser();

        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new NotFoundResponse("Error: User is not found."));

        Todo todo= todoRepository.findById(todo_id)
                .orElseThrow(() -> new NotFoundResponse("Error: Todo is not found."));

        if(todo.getUser()!=user){
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageResponse("User unauthorized to update the Entity!"));
        }

        todoRepository.deleteById(todo.getId());

        return ResponseEntity.ok().body(new MessageResponse("Todo deleted successfully!"));
    }
}
