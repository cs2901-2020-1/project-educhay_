package com.educhay.project.controllers;
import com.educhay.project.classes.Usuario;
import com.educhay.project.repository.Users_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class usersController {
    @Autowired
    Users_repository UserRepo;
    @PostMapping("/register")
    public boolean register(@RequestBody String username, String pass){
        Usuario new_usr = new Usuario(username,pass);
        UserRepo.save(new_usr);
        return true;
    }
}
