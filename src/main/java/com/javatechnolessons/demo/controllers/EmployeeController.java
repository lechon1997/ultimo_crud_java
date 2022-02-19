package com.javatechnolessons.demo.controllers;

import com.javatechnolessons.demo.model.Employee;
import com.javatechnolessons.demo.repository.IEmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    IEmployeeJpaRepository employeeRepository;

    @PostMapping()
    public ResponseEntity<Employee> crearEmpleado(@RequestBody Employee employee){
        int x = 3;
        try {
            Employee _employee = employeeRepository.save(new Employee(employee.getFirstName(),employee.getLastName(),employee.getEmployeeid(),employee.getRole()));
            return new ResponseEntity<>(_employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping()
    public ArrayList<Employee> listarEmpleados(){
        return (ArrayList<Employee>) employeeRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable("id") long id) {
        try {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>("Empleado eliminado",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> actualizarProyecto(@PathVariable("id") long id, @RequestBody Employee employee) {
        Optional<Employee> EmployeeData = employeeRepository.findById(id);

        if (EmployeeData.isPresent()) {
            Employee _employee = EmployeeData.get();
            _employee.setFirstName(employee.getFirstName());
            _employee.setLastName(employee.getLastName());
            _employee.setEmployeeid(employee.getEmployeeid());
            _employee.setRole(employee.getRole());
            return new ResponseEntity<>(employeeRepository.save(_employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

