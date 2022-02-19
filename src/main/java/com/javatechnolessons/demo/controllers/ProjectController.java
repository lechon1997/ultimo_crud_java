package com.javatechnolessons.demo.controllers;

import com.javatechnolessons.demo.model.Project;
import com.javatechnolessons.demo.repository.IProjectJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    IProjectJpaRepository projectRepository;

    @PostMapping()
    public ResponseEntity<Project> crearProyecto(@RequestBody Project project){
        try {
            Project _project = projectRepository.save(new Project(project.getName()));
            return new ResponseEntity<>(_project, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping()
    public ArrayList<Project> listarProyectos(){
        return (ArrayList<Project>) projectRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProyecto(@PathVariable("id") long id) {
        try {
            projectRepository.deleteById(id);
            return new ResponseEntity<>("Proyecto eliminado",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> actualizarProyecto(@PathVariable("id") long id, @RequestBody Project tutorial) {
        Optional<Project> ProyectData = projectRepository.findById(id);

        if (ProyectData.isPresent()) {
            Project _proyect = ProyectData.get();
            _proyect.setName(tutorial.getName());
            return new ResponseEntity<>(projectRepository.save(_proyect), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
