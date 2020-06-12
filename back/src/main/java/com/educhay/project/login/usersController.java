package com.educhay.project.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class usersController {
    @Autowired
    usersRepository UserRepo;
    @PostMapping("/register")
    public boolean register(@RequestBody String username, String pass){
        users new_usr = new users(username,pass);
        UserRepo.save(new_usr);
        return true;
    }
}
