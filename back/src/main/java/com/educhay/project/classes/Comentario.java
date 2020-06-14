package com.educhay.project.classes;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Usuario creador;
    public Date fecha;
    public Long video_id;
    public String content;
    public Comentario(Usuario _creador, Date _fecha , Long _video_id, String _content){

        creador = _creador;
        fecha = _fecha;
        video_id = _video_id;
        content = _content;
    }


}
