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

    @PostMapping("/unidades/new")
    public Register_response new_unit(@RequestBody Unidad_post unidad_post) {
        Unidad my_unidad = new Unidad();
        my_unidad.nombre = unidad_post.nombre;
        my_unidad.curso = unidad_post.curso;
        my_unidad.grado = unidad_post.grado;
        unidad_repository.save(my_unidad);
        return new Register_response();
    }

    @GetMapping("/unidades")
    @ResponseBody
    public HashMap<String,HashMap<String,unit_list>> dump() {
        HashMap<String,HashMap<String,unit_list>> finalMap = new HashMap<String,HashMap<String,unit_list>>();
        ArrayList<ArrayList<unit_list>> finalList = new ArrayList<>();
        unit_list to_return = new unit_list();
        Iterable<Unidad> my_iterable = unidad_repository.findAll();
        my_iterable.forEach(u -> {
            Unidad_response buffer = new Unidad_response();
            buffer.curso = u.curso;
            buffer.grado = u.grado;
            buffer.nombre = u.nombre;
            buffer.id = u.getId();
            ArrayList<Long> vid_id = new ArrayList<>();
            for (Video vid : u.videos) {
                vid_id.add(vid.getId());
            }
            buffer.videos = vid_id;
            to_return.add(buffer);
        });

         for (Unidad_response vid:to_return){
            if (finalMap.get(vid.grado) == null){
                HashMap<String,unit_list> buffer = new HashMap<String,unit_list>();
                finalMap.put(vid.grado,buffer);
            }
            if ((finalMap.get(vid.grado)).get(vid.curso) == null){
                unit_list buffer = new unit_list();
                finalMap.get(vid.grado).put(vid.curso,buffer);
            }
            unit_list to_append = (finalMap.get(vid.grado)).get(vid.curso);
            to_append.add(vid);
         }
    return finalMap;
    }

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
