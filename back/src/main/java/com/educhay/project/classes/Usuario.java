package com.educhay.project.classes;

import com.educhay.project.requests.Video_response;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public String nombre, apellido;
    @Convert(converter = AttributeEncryptor.class)
    public String password;
    @Column(unique = true)
    public String email;
    @OneToMany(cascade = CascadeType.ALL)
    public List<VidVisto> vids_vistos;



    public Usuario(String password, String nombre, String apellido, String email) {

        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.vids_vistos = new ArrayList<VidVisto>();
    }
    public boolean watch(Video video){
        if (!vids_vistos.contains(video)) {
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            VidVisto vidVisto = new VidVisto(video,this,date);
            this.vids_vistos.add(vidVisto);
        }
        return true;
    }

    public Usuario() { }
    ;


}
