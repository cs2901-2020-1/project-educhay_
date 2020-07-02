package com.educhay.project.repository;

import com.educhay.project.classes.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface Usuarios_repository extends CrudRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(long id);
}
