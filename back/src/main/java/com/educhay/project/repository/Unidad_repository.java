package com.educhay.project.repository;

import com.educhay.project.classes.Unidad;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

//TODO:content
public interface Unidad_repository extends CrudRepository<Unidad, Long> {
    //nombre, curso , grado;
    Unidad findByNombre(String _nombre);
    ArrayList<Unidad> findByCurso(String _curso);
    ArrayList<Unidad> findByGrado(String _grado);
}
