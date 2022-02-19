
package com.javatechnolessons.demo.controllers;

import com.javatechnolessons.demo.model.Project;
import com.javatechnolessons.demo.model.Role;
import com.javatechnolessons.demo.repository.IProjectJpaRepository;
import com.javatechnolessons.demo.repository.IRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    IRoleJpaRepository roleRepositori;

    @PostMapping()
    public ResponseEntity<Role> crearRol(@RequestBody Role role){
        try {
            Role _role = roleRepositori.save(new Role(role.getName()));
            return new ResponseEntity<>(_role, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping()
    public ArrayList<Role> listarRoles(){
        return (ArrayList<Role>) roleRepositori.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable("id") long id) {
        try {
            roleRepositori.deleteById(id);
            return new ResponseEntity<>("Rol eliminado",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> actualizarRol(@PathVariable("id") long id, @RequestBody Role role) {
        Optional<Role> RoleData = roleRepositori.findById(id);

        if (RoleData.isPresent()) {
            Role _role = RoleData.get();
            _role.setName(role.getName());
            return new ResponseEntity<>(roleRepositori.save(_role), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
