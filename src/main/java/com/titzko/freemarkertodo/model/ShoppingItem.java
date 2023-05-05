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
public class ShoppingItem {

    @Id
    @SequenceGenerator(
            name = "shopping_list_id",
            sequenceName = "shopping_list_id",
            allocationSize = 0
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "shopping_list_id"
    )
    private Long id;
    @Size(min = 3, max = 50, message = "{Name must be between 3-50 characters}")
    private String name;
    //@Min(1)
    private Double estimatedPrice;
    //@Min(1)
    private double actualPrice;
    private boolean purchased;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date purchasingDate;
    //@Min(1)
    private Integer amount;
    Priority priority;
    private Long userId;

    public ShoppingItem(Long userId) {
        this.userId = userId;
    }

}