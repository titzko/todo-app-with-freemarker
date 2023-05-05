package com.titzko.freemarkertodo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @SequenceGenerator(
            name = "task_id",
            sequenceName = "task_id",
            allocationSize = 0
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "task_id"
    )
    private Long id;
    @Size(min = 5, max = 50, message = "{Task-name must be between 5-50 characters}")
    private String title;
    private String description;
    private Priority priority;
    private boolean done;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startingDate;
    private Long userId;
    private Category category;
}
