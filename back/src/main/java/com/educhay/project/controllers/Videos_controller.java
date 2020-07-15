package com.educhay.project.controllers;

import com.educhay.project.classes.*;
import com.educhay.project.errores.OrderNotFoundException;
import com.educhay.project.repository.Profesor_repository;
import com.educhay.project.repository.Unidad_repository;
import com.educhay.project.repository.Usuarios_repository;
import com.educhay.project.repository.Video_repository;
import com.educhay.project.requests.*;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

@CrossOrigin
@RestController
public class Videos_controller {

    static class Video_list extends ArrayList<Video_response> {}
    static class Unit_list extends ArrayList<Unidad_response> {}

    @Autowired
    Video_repository video_repository;
    @Autowired
    Unidad_repository unidad_repository;
    @Autowired
    Profesor_repository profesor_repository;
    @Autowired
    Usuarios_repository usuarios_repository;

    Logger logger = LoggerFactory.getLogger(Usuarios_controller.class);
    public Video_list listEncode(List<Video> videos){
        Video_list return_list = new Video_list();
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
    }
    @Transactional
    @CrossOrigin
    @PostMapping("/videos/POST")
    public Register_response insertVideo(@RequestBody Video_request video_request){
        Optional<Profesor> profe = profesor_repository.findByEmail(video_request.creador_email);
        Optional<Unidad> unidad = unidad_repository.findByNombre(video_request._unidad);
        if (!profe.isPresent() || !unidad.isPresent()){throw new OrderNotFoundException();}
        else {
            Video vid = new Video(profe.get(),unidad.get(), video_request.url_stream, video_request.titulo, video_request.url_download,video_request.descripcion);
            List<Profesor> admins = new ArrayList<>();
            Iterable<Profesor> my_iterable= profesor_repository.findAll();
            my_iterable.forEach(profesor -> {
                if (profesor.is_admin){
                    profesor.notifs.add(vid);
                    profesor_repository.save(profesor);
                }
            });
            video_repository.save(vid);
            return new Register_response();
        }

    }
    @CrossOrigin
    @PutMapping("/videos/rate")
    public float video_rate(@RequestBody Rate_request rate_request){
        Optional<Video> my_vid_o = video_repository.findById(rate_request.video_id);
        Optional<Usuario> my_user_o =  usuarios_repository.findByEmail(rate_request.usuario_email);
        if (!my_user_o.isPresent() || !my_vid_o.isPresent()){
            throw new OrderNotFoundException();
        }
        else {
            Video vid = my_vid_o.get();
            Usuario usr = my_user_o.get();
            vid.rate(usr,rate_request.rating);
            video_repository.save(vid);
            return vid.rating;

        }
    }
    @CrossOrigin
    @GetMapping("/unit_videos/{my_id}")
    @ResponseBody
    public Video_list videosByUnit(@PathVariable(value = "my_id") Long request_id) {
        logger.error(Long.toString(request_id));
        Optional<Unidad> my_unit_o = unidad_repository.findById(request_id);
        if (my_unit_o.isPresent()) {
            Unidad my_unit = my_unit_o.get();
            ArrayList<Video> videos = video_repository.findByUnidad(my_unit);
            Video_list return_list = new Video_list();
            return_list = listEncode(videos);
            return return_list;
        } else {
            throw new OrderNotFoundException();
        }

    }
    @CrossOrigin
    @GetMapping("/videos/historicos/{email}")
    @ResponseBody
    public Video_list videosHistoricos(@PathVariable(value = "email")String email)
    {
        Optional<Usuario> optionalUsuario =usuarios_repository.findByEmail(email);
        if (optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            List<Video> videos = usuario.vids_vistos;
            Video_list video_list = listEncode(videos);
            return (video_list);
        }else throw new OrderNotFoundException();
    }
    @CrossOrigin
    @Transactional
    @PutMapping("/video/update")
    public Video_response_single updateVidId(@RequestBody Video_single_request video_single_request){
        Long request_id = video_single_request.id;
        String email = video_single_request.user_email;
        Optional<Video> my_vid_o = video_repository.findById(request_id);
        Optional<Usuario> usuarioOptional = usuarios_repository.findByEmail(email);
        if(my_vid_o.isPresent() && usuarioOptional.isPresent()){
            Video x = my_vid_o.get();
            Usuario usuario = usuarioOptional.get();
            x.addView();
            usuario.watch(x);
            Video_response_single buffer = new Video_response_single();
            buffer.creador_email = x.creador.email;
            buffer.creador_nombre = x.creador.nombre;
            buffer.creador_apellido = x.creador.apellido;
            buffer.id = x.getId();
            buffer.rating = x.rating;
            buffer.titulo = x.titulo;
            buffer.views = x.views;
            buffer.url_download = x.url_download;
            buffer.url_stream = x.url_stream;
            buffer.descripcion = x.descripcion;
            buffer.unidad = x.unidad.nombre;
            buffer.curso = x.unidad.curso;
            buffer.grado = x.unidad.grado;
            ArrayList<Comment_response> responses = new ArrayList<Comment_response>();
            for (Comentario comment:(x.comments)){
                Comment_response tmp = new Comment_response();
                tmp.video_id =x.getId();
                tmp.content = comment.content;
                tmp.fecha = comment.fecha;
                tmp.comment_id = comment.getId();
                tmp.email = comment.creador.email;
                tmp.nombre = comment.creador.nombre;
                tmp.apellido = comment.creador.apellido;
                responses.add(tmp);
            }
            buffer.comments = responses;
            return buffer;

        }else {
            throw new OrderNotFoundException();
        }
    }
    @CrossOrigin
    @PostMapping("/video")
    @ResponseBody
    public Video_response_single videosById(@RequestBody Video_single_request video_single_request)
    {
        Long request_id = video_single_request.id;
        String email = video_single_request.user_email;
        Optional<Video> my_vid_o = video_repository.findById(request_id);
        Optional<Usuario> usuarioOptional = usuarios_repository.findByEmail(email);
        if(my_vid_o.isPresent() && usuarioOptional.isPresent()){
            Video x = my_vid_o.get();
            Usuario usuario = usuarioOptional.get();
            Video_response_single buffer = new Video_response_single();
            buffer.creador_email = x.creador.email;
            buffer.creador_nombre = x.creador.nombre;
            buffer.creador_apellido = x.creador.apellido;
            buffer.id = x.getId();
            buffer.rating = x.rating;
            buffer.titulo = x.titulo;
            buffer.views = x.views;
            buffer.url_download = x.url_download;
            buffer.url_stream = x.url_stream;
            buffer.descripcion = x.descripcion;
            buffer.unidad = x.unidad.nombre;
            buffer.curso = x.unidad.curso;
            buffer.grado = x.unidad.grado;
            ArrayList<Comment_response> responses = new ArrayList<Comment_response>();
            for (Comentario comment:(x.comments)){
                Comment_response tmp = new Comment_response();
                tmp.video_id =x.getId();
                tmp.content = comment.content;
                tmp.fecha = comment.fecha;
                tmp.comment_id = comment.getId();
                tmp.email = comment.creador.email;
                tmp.nombre = comment.creador.nombre;
                tmp.apellido = comment.creador.apellido;
                responses.add(tmp);
            }
            buffer.comments = responses;
            return buffer;

        }else {
            throw new OrderNotFoundException();
        }
    }


}
