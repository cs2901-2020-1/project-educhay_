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

    @CrossOrigin
    @GetMapping("/")
    public String holaMundo() {
        String to_return = "Hola Mundo!";
        return to_return;
    }

    @CrossOrigin
    @PostMapping("/register")
    @ResponseBody
    public Register_response register(@RequestBody Register_form register_form) {
        //String username, String password, String nombre , String apellido, String email

        Optional<Usuario> u_email_validacion = UserRepo.findByEmail(register_form.email);
//        Optional<Usuario> u_user_validacion = UserRepo.findByUsername(register_form.username);
        Optional<Profesor> p_email_validacion = ProfeRepo.findByEmail(register_form.email);
        Optional<Profesor> p_user_validacion = ProfeRepo.findByUsername(register_form.username);
        if (u_email_validacion.isPresent()/* || u_user_validacion.isPresent()*/ || p_email_validacion.isPresent() || p_user_validacion.isPresent()) {
            Register_response toReturn = new Register_response();
            toReturn.confirmation = false;
        }

        if (register_form.email.equals("profe@utec.edu.pe")) {
            Profesor new_profe = new Profesor(register_form.username, register_form.password, register_form.nombre, register_form.apellido, register_form.email);
            ProfeRepo.save(new_profe);
        } else {
            Usuario new_usr = new Usuario(register_form.username, register_form.password, register_form.nombre, register_form.apellido, register_form.email);
            UserRepo.save(new_usr);
        }

        return new Register_response();
    }

    @CrossOrigin
    @PostMapping("/login_temp")
    @ResponseBody
    Login_response login_temp(@RequestBody Login_request request) {
        Optional<Usuario> u_email_validacion = UserRepo.findByEmail(request.email);
        Optional<Profesor> p_email_validacion = ProfeRepo.findByEmail(request.email);

        boolean one = u_email_validacion.isPresent();

        if (one) {
            String repo_pass = u_email_validacion.get().password;
            boolean two = (request.password.equals(repo_pass));
            if (two) {
                logger.error("Entro");
                Usuario my_user = u_email_validacion.get();
                Login_response to_return = new Login_response();
                to_return.apellido = my_user.apellido;
                to_return.email = my_user.email;
                to_return.is_profe = false;
                to_return.nombre = my_user.nombre;
                to_return.username = my_user.username;
                return to_return;

            } else {
                return new Login_response();
            }
        } else if (p_email_validacion.isPresent()) {
            if (request.password.equals(p_email_validacion.get().password)) {
                Profesor my_user = p_email_validacion.get();
                Login_response to_return = new Login_response();
                to_return.apellido = my_user.apellido;
                to_return.email = my_user.email;
                to_return.is_profe = true;
                to_return.is_admin = my_user.is_admin;
                to_return.nombre = my_user.nombre;
                to_return.username = my_user.username;
                return to_return;

            } else {
                return new Login_response();
            }
        } else {
            return new Login_response();
        }
    }
}
