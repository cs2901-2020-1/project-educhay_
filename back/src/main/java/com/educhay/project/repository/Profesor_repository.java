package com.educhay.project.repository;

import com.educhay.project.classes.Profesor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface Profesor_repository extends CrudRepository<Profesor,Long> {
    Optional<Profesor> findByUsername(String username);
    Optional<Profesor> findByEmail(String email);
    Optional<Profesor> findById (long id);
}
