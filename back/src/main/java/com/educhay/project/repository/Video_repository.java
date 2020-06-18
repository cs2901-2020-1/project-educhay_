package com.educhay.project.repository;
import com.educhay.project.classes.Unidad;
import com.educhay.project.classes.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface Video_repository extends CrudRepository<Video,Long>{
    ArrayList<Video> findByCreador(String _creador);
    Video findByTitulo(String _titulo);
    ArrayList<Video> findByUnidad(Unidad _unidad);



}
