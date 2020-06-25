package com.educhay.project.controllers;

import com.educhay.project.classes.Profesor;
import com.educhay.project.classes.Unidad;
import com.educhay.project.classes.Video;
import com.educhay.project.errores.OrderNotFoundException;
import com.educhay.project.repository.Profesor_repository;
import com.educhay.project.repository.Unidad_repository;
import com.educhay.project.repository.Video_repository;
import com.educhay.project.requests.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin
@RestController
public class Videos_controller {
    static class video_list extends ArrayList<Video_response> {
    }

    static class unit_list extends ArrayList<Unidad_response> {
    }

    @Autowired
    Video_repository video_repository;
    @Autowired
    Unidad_repository unidad_repository;
    @Autowired
    Profesor_repository profesor_repository;
    Logger logger = LoggerFactory.getLogger(Usuarios_controller.class);


    @PostMapping("/videos/POST")
    public Register_response insertVideo(@RequestBody Video_request video_request){
        Optional<Profesor> profe = profesor_repository.findByEmail(video_request.creador_email);
        Optional<Unidad> unidad = unidad_repository.findByNombre(video_request._unidad);
        if (!profe.isPresent() || !unidad.isPresent()){throw new OrderNotFoundException();}
        else {
            Video vid = new Video(profe.get(),unidad.get(), video_request.url_stream, video_request.titulo, video_request.url_download);
            video_repository.save(vid);
            return new Register_response();
        }
    }

    @PostMapping("/unit_videos/{my_id}")
    @ResponseBody
    public video_list videosByUnit(@PathVariable(value = "my_id") Long request_id) {
        logger.error(Long.toString(request_id));
        Optional<Unidad> my_unit_o = unidad_repository.findById(request_id);
        if (my_unit_o.isPresent()) {
            Unidad my_unit = my_unit_o.get();
            ArrayList<Video> videos = video_repository.findByUnidad(my_unit);
            video_list return_list = new video_list();
            for (Video x : videos) {
                Video_response buffer = new Video_response();
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
        } else {
            throw new OrderNotFoundException();
        }

    }

}
