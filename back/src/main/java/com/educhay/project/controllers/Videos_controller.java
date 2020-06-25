package com.educhay.project.controllers;

import com.educhay.project.classes.Unidad;
import com.educhay.project.classes.Video;
import com.educhay.project.repository.Unidad_repository;
import com.educhay.project.repository.Video_repository;
import com.educhay.project.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;



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



    @PostMapping("/unit_videos")
    @ResponseBody
    public video_list videosByUnit(@RequestBody Videos_by_unit_request request) {
        Optional<Unidad> my_unit_o = unidad_repository.findByNombre(request.nombre);
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
            video_list empty_list = new video_list();
            return empty_list;
        }

    }

}
