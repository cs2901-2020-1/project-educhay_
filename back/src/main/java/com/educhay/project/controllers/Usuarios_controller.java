package com.educhay.project.controllers;
import com.educhay.project.classes.Usuario;
import com.educhay.project.repository.Usuarios_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Usuarios_controller {
    @Autowired
    Usuarios_repository UserRepo;
    @PostMapping("/register")
    public boolean register(@RequestBody String username, String pass){
        Usuario new_usr = new Usuario(username,pass);
        UserRepo.save(new_usr);
        return true;
    }
}
