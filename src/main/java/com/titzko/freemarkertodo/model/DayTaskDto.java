package com.titzko.freemarkertodo.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DayTaskDto {

    Date date;
    List<Task> tasks;
    Integer amount;
}
