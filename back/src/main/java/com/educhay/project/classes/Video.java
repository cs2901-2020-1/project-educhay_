package com.educhay.project.classes;

import antlr.CppCodeGenerator;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Video {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    public Long counter;
    //For the rating
    @OneToOne
    public Profesor creador;
    public Float rating;
    @OneToOne
    public Unidad unidad;
    public String url_stream;

    public String titulo;
    public String url_download;
    @OneToMany
    List<Comentario> comments;
    //comments are to be loaded by video id
    @OneToMany
    List<Usuario> rating_users;

    boolean rate(Usuario _usuario, int _rating) {
        if (rating_users.contains(_usuario)) {
            return false;
        }
        rating_users.add(_usuario);
        rating = ((rating * counter) + _rating) / counter + 1;
        counter++;
        return true;
    }

    boolean comment(String content, Usuario _usuario, Date fecha) {
        Comentario new_comment = new Comentario(_usuario, fecha, id, content);
        return true;
    }
    public  Video(){}

    public Long getId() {
        return id;
    }
    public Video(Profesor _creador, Unidad _unidad, String _url_stream, String _titulo, String _url_download){
        creador = _creador;
        unidad = _unidad;
        url_stream = _url_stream;
        url_download = _url_download;
        titulo = _titulo;

        counter = 0L;
        rating = 0F;
        comments = new ArrayList<Comentario>();
        rating_users = new ArrayList<Usuario>();
        unidad.addVideo(this);
        creador.addVideoToProfesor(this);
    }
    //TODO: Default constructor,meter el video a todas las listas necesarias en construccion, Play, download, jalar fecha y el usuario de la sesion, quitarlos como parametro
}
