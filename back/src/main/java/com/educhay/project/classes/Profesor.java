package com.educhay.project.classes;

import com.educhay.project.repository.Video_repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Profesor extends Usuario {
    public Boolean is_SUPER_admin;
    //TODO
    public Boolean is_admin;
    //TODO: hacer todo con is_admin , borrar cuentas o subir a profe
    @OneToMany(cascade = CascadeType.ALL)
    public List<Video> videos;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Video> notifs;
    public Profesor(){};
    public List<Video>verNotifs(){
        List<Video> to_re = notifs;
        notifs = new ArrayList<Video>();
        return to_re;
    }
    public Profesor(Usuario usuario){
        apellido = usuario.apellido;
        email=usuario.email;
        nombre = usuario.nombre;
        apellido = usuario.apellido;
        password = usuario.password;
        is_admin = false;
        notifs = new ArrayList<Video>();
        videos =  new ArrayList<Video>();
        //TODO:Make api remove from usuario repo after this

    }
    public Profesor( String _password, String _nombre, String _apellido, String _email) {

        password = _password;
        nombre = _nombre;
        apellido = _apellido;
        email = _email;
        is_admin = true;
        videos =  new ArrayList<Video>();
        notifs = new ArrayList<Video>();
    }

    public boolean addVideoToProfesor(Video _video) {
        if (videos.contains(_video)) {
            return false;
        }
        videos.add(_video);
        return true;
    }

    public boolean deleteVideo(Video_repository repo, Long video_id) {
        Optional<Video> myVid_o = repo.findById(video_id);
        if (myVid_o.isPresent()) {
            Video myVid = myVid_o.get();
            if (myVid.creador == this) {
                repo.deleteById(video_id);
                return true;
            }
        }
        return false;
    }

    public boolean moveUnidad(Video video, Unidad unidad) {
        if (video.creador == this) {
            video.unidad = unidad;
            return true;
        }
        return false;
    }

    public boolean deleteComment(Video video, int index) {
        if (video.creador == this) {
            video.comments.remove(index);
            return true;
        }
        return false;
    }

}

