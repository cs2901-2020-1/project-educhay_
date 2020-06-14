package com.educhay.project.classes;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    ArrayList<Long> videos;
    public String nombre, curso , grado;
    public boolean addVideo(Long id){
        if (videos.contains(id)){return false;}
        videos.add(id);
        return true;
    }
}
