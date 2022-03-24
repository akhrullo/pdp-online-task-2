package com.example.theme_10_2.payload;

import com.example.theme_10_2.entity.Address;
import com.example.theme_10_2.entity.Group;
import com.example.theme_10_2.entity.Subject;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * @author Saydali Murodullayev, Sun 4:08 PM. 3/13/2022
 */
@Data
public class StudentCreateDto {

    private String firstName;

    private String lastName;

    private Integer address_id;

    private Integer group_id;
}
