package com.educhay.project.controllers;

import com.educhay.project.classes.Unidad;
import com.educhay.project.classes.Video;
import com.educhay.project.repository.Unidad_repository;
import com.educhay.project.requests.Register_response;
import com.educhay.project.requests.Unidad_post;
import com.educhay.project.requests.Unidad_response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
@CrossOrigin
@RestController
public class Unidades_controller {
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
    public HashMap<String, HashMap<String, Videos_controller.unit_list>> dump() {
        HashMap<String,HashMap<String, Videos_controller.unit_list>> finalMap = new HashMap<String,HashMap<String, Videos_controller.unit_list>>();
        ArrayList<ArrayList<Videos_controller.unit_list>> finalList = new ArrayList<>();
        Videos_controller.unit_list to_return = new Videos_controller.unit_list();
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
                HashMap<String, Videos_controller.unit_list> buffer = new HashMap<String, Videos_controller.unit_list>();
                finalMap.put(vid.grado,buffer);
            }
            if ((finalMap.get(vid.grado)).get(vid.curso) == null){
                Videos_controller.unit_list buffer = new Videos_controller.unit_list();
                finalMap.get(vid.grado).put(vid.curso,buffer);
            }
            Videos_controller.unit_list to_append = (finalMap.get(vid.grado)).get(vid.curso);
            to_append.add(vid);
        }
        return finalMap;
    }

}
