package com.educhay.project.login;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface userRepository extends CrudRepository<user, Long> {
    user findByUsername(String username);
    user findById (long id);
}
