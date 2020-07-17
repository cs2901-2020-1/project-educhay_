package com.educhay.project.controllers;

import com.educhay.project.classes.Notif;
import com.educhay.project.classes.Profesor;
import com.educhay.project.classes.Video;
import com.educhay.project.errores.OrderNotFoundException;
import com.educhay.project.repository.Profesor_repository;
import com.educhay.project.requests.Notif_response;
import com.educhay.project.requests.Video_response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class Notif_controller {

    @Autowired
    Profesor_repository profesor_repository;
    @Transactional
    @GetMapping("/notif/{email}")
    @ResponseBody
    public List<Notif_response> notifs(@PathVariable(value = "email") String my_email){
        Optional<Profesor> optionalProfesor = profesor_repository.findByEmail(my_email);
        if (optionalProfesor.isPresent()) {
            Profesor profesor = optionalProfesor.get();
            List<Notif_response> array = profesor.verNotifs();
            profesor_repository.save(profesor);
            return array;
        }else {throw new OrderNotFoundException();}

    }


}
