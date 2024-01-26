package com.openclassroom.api.controller;

import com.openclassroom.api.model.Employee;
import com.openclassroom.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService es;

    @GetMapping("/")
    public String sayHello(){
        return "Hello World !";
    }

    /**
     * Read - Get all employees
     * @return - An Iterable object of Employee full filled
     */
    @GetMapping(path="/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> getAllEmployees(){
        return es.getEmployees();
    }

    /**
     * Read - Get one employee
     * @param id The id of the employee
     * @return An Employee object full filled
     */
    @GetMapping(path="/employee/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getAnEmployee(@PathVariable(name="id") final Long id){
        Optional<Employee> isEmployee = es.getEmployee(id);
        return isEmployee.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    /**
     * Delete - Delete an employee
     * @param id - The id of the employee to delete
     */
    @DeleteMapping(path="/employee/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnEmployee(@PathVariable(name="id") final Long id){
        es.deleteEmployee(id);
    }

    /**
     * Create - Add a new employee
     * @param employee An object employee
     * @return The employee object saved
     */
    @PostMapping(
            path="/employee",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(es.createEmployee(employee), HttpStatus.CREATED);
    }

    /**
     * Update - Update an existing employee
     * @param id - The id of the employee to update
     * @param employee - The employee object updated
     * @return
     */
    @PutMapping(
            path="/employee/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") final Long id, @RequestBody Employee employee) {
        Optional<Employee> e = es.getEmployee(id);
        if(e.isEmpty()){
           return null;
        }
        Employee currentEmployee = e.get();

        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        String mail = employee.getMail();
        String password = employee.getPassword();
        if (firstName != null) currentEmployee.setFirstName(firstName);
        if (lastName!= null) currentEmployee.setLastName(lastName);
        if (mail!= null) currentEmployee.setMail(mail);
        if (password!= null) currentEmployee.setPassword(password);

        return new ResponseEntity<>(es.updateEmployee(currentEmployee),HttpStatus.OK);
    }
}
