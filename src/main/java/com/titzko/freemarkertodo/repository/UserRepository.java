package com.titzko.freemarkertodo.repository;

import com.titzko.freemarkertodo.model.MyUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<MyUser, Long> {

    @Query("SELECT u FROM MyUser u WHERE u.username = :username")
    MyUser getUserByUsername(@Param("username") String username);
}

