package com.titzko.freemarkertodo;

import com.titzko.freemarkertodo.model.MyUser;
import com.titzko.freemarkertodo.repository.TaskRepository;
import com.titzko.freemarkertodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;


    public MyCommandLineRunner(TaskRepository repository) {
        this.taskRepository = repository;
    }


    @Override
    public void run(String... args) throws Exception {

        MyUser user1 = new MyUser("titzko","$2a$10$F4t8JH4cXdV98ecJFpmj0.WDYI6LkC3yj.ANsEsDrg5bgBsmiJcki","admin", true);

        List<MyUser> userList = List.of(user1);
        userRepository.saveAll(userList);

    }
}


