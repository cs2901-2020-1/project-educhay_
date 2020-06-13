package com.educhay.project.repository;

import com.educhay.project.classes.Users;
import org.springframework.data.repository.CrudRepository;

public interface usersRepository extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
    Users findById (long id);
}
