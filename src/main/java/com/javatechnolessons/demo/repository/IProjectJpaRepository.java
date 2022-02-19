package com.javatechnolessons.demo.repository;

import com.javatechnolessons.demo.model.Project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Project JPA Interface
 * @author javatechnolessons
 * @version 1.0
 */
@Repository
public interface IProjectJpaRepository extends CrudRepository<Project, Long> {
    Project findByName(String name);
}
