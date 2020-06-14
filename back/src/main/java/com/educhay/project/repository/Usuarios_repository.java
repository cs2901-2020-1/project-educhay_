package com.educhay.project.repository;

import com.educhay.project.classes.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface Usuarios_repository extends CrudRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    Usuario findById (long id);
}
