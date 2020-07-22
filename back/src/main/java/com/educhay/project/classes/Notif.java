package com.educhay.project.classes;

import com.educhay.project.requests.Notif_response;

import javax.persistence.*;

@Entity
public class Notif {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    public Profesor profesor;
    @ManyToOne
    public Video video;
    boolean read;
    public Notif(Profesor _profesor, Video _video){
        profesor = _profesor;
        video = _video;
        read = false;
    }
    public Notif(){};
    public Notif_response encapsulate(){
        Notif_response notif_response = new Notif_response();
        notif_response.profesor_email = profesor.email;
        notif_response.read = read;
        notif_response.video_id = video.getId();
        notif_response.video_nombre=video.titulo;
        return notif_response;
    }
}
