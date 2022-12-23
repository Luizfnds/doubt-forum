package com.lefnds.doubtforum.controlers;

import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserControler {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable UUID id) {
        User user = (User) userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
