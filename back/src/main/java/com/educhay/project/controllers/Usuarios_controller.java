package com.educhay.project.controllers;

import com.educhay.project.classes.AttributeEncryptor;
import com.educhay.project.classes.Profesor;
import com.educhay.project.classes.Usuario;
import com.educhay.project.errores.OrderNotFoundException;
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
import java.util.PropertyPermission;
@CrossOrigin
@RestController
public class Usuarios_controller {
    @Autowired
    Usuarios_repository UserRepo;
    @Autowired
    Profesor_repository ProfeRepo;
    AttributeEncryptor attributeEncryptor;
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

        Optional<Profesor> p_email_validacion = ProfeRepo.findByEmail(register_form.email);

        if (u_email_validacion.isPresent() || p_email_validacion.isPresent() ) {
            Register_response toReturn = new Register_response();
            toReturn.confirmation = false;
        }

        if (register_form.email.equals("profe@utec.edu.pe")) {
            Profesor new_profe = new Profesor(register_form.password, register_form.nombre, register_form.apellido, register_form.email);
            ProfeRepo.save(new_profe);
            logger.error("profe saved");
        } else {
            Usuario new_usr = new Usuario(register_form.password, register_form.nombre, register_form.apellido, register_form.email);
            UserRepo.save(new_usr);
            logger.error("no deberia entrar");
        }

        return new Register_response();
    }

    @CrossOrigin
    @PostMapping("/login_temp")
    @ResponseBody
    Login_response login_temp(@RequestBody Login_request request) {
        Optional<Usuario> u_email_validacion = UserRepo.findByEmail(request.email);
        Optional<Profesor> p_email_validacion = ProfeRepo.findByEmail(request.email);

        logger.error(String.valueOf(u_email_validacion.isPresent()));
        logger.error(String.valueOf(p_email_validacion.isPresent()));

        boolean one = u_email_validacion.isPresent();
        boolean pone = p_email_validacion.isPresent();
        if (pone) {
            Profesor profe = p_email_validacion.get();
            String pass = profe.password;
            logger.error(pass);

            if (request.password.equals(pass)) {
                Profesor my_user = p_email_validacion.get();
                Login_response to_return = new Login_response();
                to_return.apellido = my_user.apellido;
                to_return.email = my_user.email;
                to_return.is_profe = true;
                to_return.is_admin = my_user.is_admin;
                to_return.nombre = my_user.nombre;

                return to_return;

            }else {
                throw new OrderNotFoundException();
            }

        } else if (one) {
            logger.error(u_email_validacion.get().password);
            String repo_pass = u_email_validacion.get().password;
            String epass = repo_pass;
            boolean two = (request.password.equals(epass));
            if (two) {
                logger.error("Entro");
                Usuario my_user = u_email_validacion.get();
                Login_response to_return = new Login_response();
                to_return.apellido = my_user.apellido;
                to_return.email = my_user.email;
                to_return.is_profe = false;
                to_return.nombre = my_user.nombre;

                return to_return;

            } else {
                throw new OrderNotFoundException();
            }
        } else {
            throw new OrderNotFoundException();
        }
    }
}
