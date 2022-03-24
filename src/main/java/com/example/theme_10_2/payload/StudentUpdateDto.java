package com.example.theme_10_2.payload;

import lombok.Data;



@Data
public class StudentUpdateDto {

    private Integer id;
    private String firstName;

    private String lastName;

    private Integer address_id;

    private Integer group_id;

}
