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
    public Long counter ;
    @OneToOne
    public Profesor creador;
    public Float rating;
    public String unidad;
    public String url_stream;
    public String nombre ;
    public String titulo ;
    public String url_download;
    @OneToMany
    List<Comentario> comments;
    @ElementCollection
    List<Long> rating_users;
    boolean rate(Long _usuario, int _rating){
        if (rating_users.contains(_usuario)){
            return false;
        }
        rating_users.add(_usuario);
        rating = ((rating * counter) + _rating)/counter+1;
        counter ++;
        return true;
    }
    boolean comment(String content , Usuario _usuario, Date fecha){
        Comentario new_comment  = new Comentario(_usuario , fecha, id, content);
        return true;
    }
    //TODO: Default constructor,meter el video a todas las listas necesarias en construccion, Play, download, jalar fecha y el usuario de la sesion, quitarlos como parametro
}
