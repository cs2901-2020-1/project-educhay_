package com.educhay.project.classes;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany
    List<Video> videos;
    public String nombre, curso , grado;
    public boolean addVideo(Video id){
        if (videos.contains(id)){return false;}
        videos.add(id);
        return true;
    }
}
