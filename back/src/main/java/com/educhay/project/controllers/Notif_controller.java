package com.educhay.project.controllers;

import com.educhay.project.classes.Profesor;
import com.educhay.project.classes.Video;
import com.educhay.project.errores.OrderNotFoundException;
import com.educhay.project.repository.Profesor_repository;
import com.educhay.project.requests.Video_response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class Notif_controller {

    @Autowired
    Profesor_repository profesor_repository;
    @Transactional
    @PostMapping("/notif/{email}")
    @ResponseBody
    public Videos_controller.Video_list notifs(@PathVariable(value = "email") String my_email){
        Optional<Profesor> optionalProfesor = profesor_repository.findByEmail(my_email);
        if (optionalProfesor.isPresent()) {
            Profesor profesor = optionalProfesor.get();
            List<Video> array = profesor.verNotifs();
            profesor_repository.save(profesor);
            Videos_controller.Video_list return_list = new Videos_controller.Video_list();
            for (Video x:array){
                Video_response buffer =  new Video_response();
                buffer.creador_email = x.creador.email;
                buffer.id = x.getId();
                buffer.rating = x.rating;
                buffer.titulo = x.titulo;
                buffer.Unidad = x.unidad.nombre;
                buffer.url_download = x.url_download;
                buffer.url_stream = x.url_stream;
                return_list.add(buffer);
            }

            return return_list;

        }else {throw new OrderNotFoundException();}

    }


}
