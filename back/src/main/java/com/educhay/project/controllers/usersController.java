package com.educhay.project.controllers;
import com.educhay.project.classes.Users;
import com.educhay.project.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class usersController {
    @Autowired
    usersRepository UserRepo;
    @PostMapping("/register")
    public boolean register(@RequestBody String username, String pass){
        Users new_usr = new Users(username,pass);
        UserRepo.save(new_usr);
        return true;
    }
}
