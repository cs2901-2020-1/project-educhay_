package com.educhay.project.classes;

import com.educhay.project.requests.VidVisto_response;

import javax.persistence.*;
import java.util.Date;
import java.util.zip.DataFormatException;
@Entity
public class VidVisto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    Video video;
    @ManyToOne
    Usuario usuario;
    Date date;
    public VidVisto(){};
    public VidVisto(Video video, Usuario usuario, Date date) {
        this.video = video;
        this.usuario = usuario;
        this.date = date;
    }
    public VidVisto_response encapsulate(){
        VidVisto_response buffer = new VidVisto_response();
        buffer.fecha = date;
        buffer.creador_email = video.creador.email;
        buffer.id = video.getId();
        buffer.rating = video.rating;
        buffer.titulo = video.titulo;
        buffer.url_download = video.url_download;
        buffer.url_stream = video.url_stream;
        buffer.unidad = video.unidad.nombre;
        return buffer;
    }
}
