package ca.jdelreyes.userservice.controller;

import ca.jdelreyes.userservice.dto.CreateUserRequest;
import ca.jdelreyes.userservice.dto.UpdateUserRequest;
import ca.jdelreyes.userservice.dto.UserResponse;
import ca.jdelreyes.userservice.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("{query}")
    public ResponseEntity<UserResponse> getUserByIdOrUserName(@PathVariable("query") String query) {
        UserResponse userResponse;

        try {
            userResponse = userService.getUserById(Long.parseLong(query, 10));
        } catch (Exception exception) {
            userResponse = userService.getUserByUserName((String) query);
        }

        if (userResponse == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@RequestBody() @Valid CreateUserRequest createUserRequest) {
        UserResponse userResponse = userService.createUser(createUserRequest);

        if (userResponse == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("userId") Long userId, @RequestBody() @Valid UpdateUserRequest updateUserRequest) {
        UserResponse userResponse = userService.updateUser(userId, updateUserRequest);

        if (userResponse == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
