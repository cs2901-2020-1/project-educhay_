package com.educhay.project.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class userController {
    @Autowired
    userRepository UserRepo;
    @PostMapping("/register")
    public boolean register(@RequestBody String username, String pass){
        user new_usr = new user(username,pass);
        UserRepo.save(new_usr);
        return true;
    }
}
