package com.educhay.project.controllers;
import com.educhay.project.classes.Profesor;
import com.educhay.project.classes.Usuario;
import com.educhay.project.repository.Profesor_repository;
import com.educhay.project.repository.Usuarios_repository;
import com.educhay.project.requests.Login_request;
import com.educhay.project.requests.Login_response;
import com.educhay.project.requests.Register_form;
import com.educhay.project.requests.Register_response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class Usuarios_controller {
    @Autowired
    Usuarios_repository UserRepo;
    @Autowired
    Profesor_repository ProfeRepo;
    Logger logger = LoggerFactory.getLogger(Usuarios_controller.class);
    @GetMapping("/")
    public String holaMundo(){
        String to_return = "Hola Mundo!";
        return to_return;
    }
    @PostMapping("/register")
    @ResponseBody
    public Register_response register(@RequestBody Register_form register_form){
        //String username, String password, String nombre , String apellido, String email
        logger.error("Entro");
        Optional<Usuario> u_email_validacion =UserRepo.findByEmail(register_form.email);
        Optional<Usuario> u_user_validacion = UserRepo.findByUsername(register_form.username);
        logger.error("Linea 35");
        Optional<Profesor>p_email_validacion = ProfeRepo.findByEmail(register_form.email);
        Optional<Profesor>p_user_validacion = ProfeRepo.findByUsername(register_form.username);
        if (u_email_validacion.isPresent() || u_user_validacion.isPresent() || p_email_validacion.isPresent() || p_user_validacion.isPresent() ){
            Register_response toReturn= new Register_response();
            toReturn.confirmation = false;
            logger.error("Return 1");

            return toReturn;
        }
        Usuario new_usr = new Usuario(register_form.username,register_form.password,register_form.nombre,register_form.apellido,register_form.email);
        UserRepo.save(new_usr);
        logger.error("Return 2");

        return new Register_response();
    }
    @PostMapping ("/login_temp")
    @ResponseBody
    Login_response login_temp(@RequestBody Login_request request){
        Optional<Usuario> u_email_validacion =UserRepo.findByEmail(request.email);
        Optional<Profesor>p_email_validacion = ProfeRepo.findByEmail(request.email);
        if (u_email_validacion.isPresent() && request.password == u_email_validacion.get().password){
            Usuario my_user = u_email_validacion.get();
            Login_response to_return =  new Login_response();
            to_return.apellido = my_user.apellido;
            to_return.email = my_user.email;
            to_return.is_profe = false;
            to_return.nombre = my_user.nombre;
            to_return.username = my_user.username;
            return to_return;

        }
        if (p_email_validacion.isPresent() && request.password == p_email_validacion.get().password){
            Profesor my_user = p_email_validacion.get();
            Login_response to_return =  new Login_response();
            to_return.apellido = my_user.apellido;
            to_return.email = my_user.email;
            to_return.is_profe = true;
            to_return.nombre = my_user.nombre;
            to_return.username = my_user.username;
            return to_return;

        }
        else {return new Login_response();}
    }
}
