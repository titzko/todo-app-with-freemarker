package com.titzko.freemarkertodo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3,  message = "{Username must have at least 3 characters}")
    @Size(max = 50, message = "{Username cant be longer than 50 characters}")
    private String username;
    @Size(min = 3, message = "{Password must have at least 3 characters}")
    @Size(max = 300, message = "{Password cant be longer than 300 characters}")
    private String password;
    private String role;
    private boolean enabled;


    public MyUser(String username, String password, String role, boolean enabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }
}
