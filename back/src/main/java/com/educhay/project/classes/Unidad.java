package com.educhay.project.classes;
import java.util.ArrayList;
public class Unidad {

    ArrayList<Long> videos;
    public String nombre, curso , grado;
    public boolean addVideo(Long id){
        videos.add(id);
        return true;
    }
}
