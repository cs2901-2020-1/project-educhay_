package com.educhay.project.login;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface usersRepository extends CrudRepository<users, Long> {
    users findByUsername(String username);
    users findById (long id);
}
