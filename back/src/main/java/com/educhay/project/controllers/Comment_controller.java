package com.educhay.project.controllers;

import com.educhay.project.classes.Usuario;
import com.educhay.project.classes.Video;
import com.educhay.project.errores.OrderNotFoundException;
import com.educhay.project.repository.Profesor_repository;
import com.educhay.project.repository.Unidad_repository;
import com.educhay.project.repository.Usuarios_repository;
import com.educhay.project.repository.Video_repository;
import com.educhay.project.requests.Comment_delete;
import com.educhay.project.requests.Comment_request;
import com.educhay.project.requests.Register_response;
import org.apache.tomcat.jni.Error;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin
@RestController
public class Comment_controller {
    @Autowired
    Video_repository video_repository;
    @Autowired
    Unidad_repository unidad_repository;
    @Autowired
    Profesor_repository profesor_repository;
    @Autowired
    Usuarios_repository usuarios_repository;
    Logger logger = LoggerFactory.getLogger(Usuarios_controller.class);
    @CrossOrigin
    @PostMapping("comments/POST")
    public Register_response insertComment(@RequestBody Comment_request comment_request){
        Optional<Video> video_o = video_repository.findById(comment_request.video_id);
        Optional<Usuario> usuarioOptional = usuarios_repository.findByEmail(comment_request.creador_email);
        if (video_o.isPresent() && usuarioOptional.isPresent()){
            Video video = video_o.get();
            Usuario usuario = usuarioOptional.get();
            if (video.comment(comment_request.content,usuario,comment_request.fecha)) {
                video_repository.save(video);
                return new Register_response();
            }
            else throw new OrderNotFoundException();
        }
        else{
            throw new OrderNotFoundException();
        }
    }
    @CrossOrigin
    @PutMapping("comments/delete")
    public Register_response deleteComment(@RequestBody Comment_delete comment_delete){
        Optional<Video> videoOptional = video_repository.findById(comment_delete.video_id);
        Optional<Usuario> usuarioOptional = usuarios_repository.findByEmail(comment_delete.creador_email);
        if(videoOptional.isPresent() &&  usuarioOptional.isPresent()){
            Video video = videoOptional.get();
            Usuario usuario = usuarioOptional.get();
            video.deleteComment(comment_delete.comment_id,usuario);
            video_repository.save(video);
            return new Register_response();
        }
        else { throw new OrderNotFoundException(); }}}

