package com.educhay.project.classes;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn
    public Usuario creador;
    public Date fecha;
    public Long video_id;
    public String content;
    public Comentario(){}
    public Comentario(Usuario _creador, Date _fecha , Long _video_id, String _content){

        creador = _creador;
        fecha = _fecha;
        video_id = _video_id;
        content = _content;
    }


}
