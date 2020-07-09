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
    @OneToOne(cascade = CascadeType.ALL)

    public Unidad unidad;

    public String url_stream;
    public String descripcion;
    public Long views;
    public String titulo;
    public String url_download;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Comentario> comments;
    //comments are to be loaded by video id
    @OneToMany(cascade = CascadeType.ALL)
    List<Rating> ratings;


    public boolean rate(Usuario _usuario, int _rating) {
        Boolean my_bool = true;
        Rating buffer = null;
        for (Rating previous_rate :ratings){
            if (previous_rate.user == _usuario){
                float tot = rating*counter;
                counter = counter -1;
                tot = tot - previous_rate.score;
                if (counter != 0){
                    rating = tot / counter;
                }else {
                    rating = 0F;
                };
                buffer = previous_rate;
            }
        }
        if (buffer != null){
            ratings.remove(buffer);
        };

        Rating new_rate = new Rating();
        new_rate.user = _usuario;
        new_rate.score = _rating;
        new_rate.vid = this;
        ratings.add(new_rate);
        rating = ((rating * counter) + _rating) / (counter + 1);
        counter++;
        return true;
    }

    public boolean comment(String content, Usuario _usuario, Date fecha) {
        Comentario new_comment = new Comentario(_usuario, fecha, content);
        comments.add(new_comment);
        return true;
    }

    public boolean deleteComment(Long commentId, Usuario usuario) {
        for (Comentario comment : comments) {
            if ((comment.getId() ).equals(commentId) ) {
                comments.remove(comment);
                return true;
            }
        }
        return false;
    }
    public Long addView(){this.views = views + 1;
    return views;}

    public Video() {
    }

    public Long getId() {
        return id;
    }

    public Video(Profesor _creador, Unidad _unidad, String _url_stream, String _titulo, String _url_download, String _descripcion) {
        creador = _creador;
        unidad = _unidad;
        url_stream = _url_stream;
        url_download = _url_download;
        titulo = _titulo;
        descripcion = _descripcion;
        counter = 0L;
        views = 0L;
        rating = 0F;
        comments = new ArrayList<Comentario>();
        ratings = new ArrayList<Rating>();
        unidad.addVideo(this);
        creador.addVideoToProfesor(this);
    }
    //TODO: Default constructor,meter el video a todas las listas necesarias en construccion, Play, download, jalar fecha y el usuario de la sesion, quitarlos como parametro
}
