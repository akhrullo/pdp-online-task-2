package com.example.theme_10_2.repository;

import com.example.theme_10_2.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    boolean existsByName(String name);

}
