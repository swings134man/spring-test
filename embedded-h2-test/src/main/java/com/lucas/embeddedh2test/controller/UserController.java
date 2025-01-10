package com.lucas.embeddedh2test.controller;

import com.lucas.embeddedh2test.model.UsersEntity;
import com.lucas.embeddedh2test.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UsersEntity> saveUsers(@RequestBody UsersEntity usersEntity) {
        UsersEntity data = userService.saveUser(usersEntity);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersEntity> findByIdUser(@PathVariable Long id) {
        UsersEntity data = userService.findById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UsersEntity> findByIdEmail(@PathVariable String email) {
        UsersEntity data = userService.findByEmail(email);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UsersEntity> updateUser(@RequestBody UsersEntity user) {
        UsersEntity entity = userService.update(user);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
