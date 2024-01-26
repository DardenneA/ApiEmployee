package com.openclassroom.api.service;

import com.openclassroom.api.model.Employee;
import com.openclassroom.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    //get one
    public Optional<Employee> getEmployee(final Long id){
        return employeeRepository.findById(id);
    }

    //get all
    public Iterable<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    //delete
    public void deleteEmployee(final Long id){
        employeeRepository.deleteById(id);
    }

    //add
   public Employee createEmployee(Employee employee){
       return employeeRepository.save(employee);
   }

   public Employee updateEmployee(Employee employee){
        if(employeeRepository.existsById(employee.getId())){
            return employeeRepository.save(employee);
        }
       return null;
   }
}
