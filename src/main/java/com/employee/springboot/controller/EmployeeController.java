package com.employee.springboot.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.springboot.repository.*;
import com.employee.springboot.entity.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeRep employeeRepository;

    @Autowired
    private IDepartmentRep departmentRepository;

    @Autowired
    private IJobsRep jobRepository;

    @Autowired
    private ILocationRep locationRepository;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        // Fetch the related objects based on their IDs
        Department department = departmentRepository.findById(employee.getDepartment().getDepartmentId()).orElse(null);
        Jobs job = jobRepository.findById(employee.getJob().getJobId()).orElse(null);
        Location location = locationRepository.findById(employee.getLocation().getLocationId()).orElse(null);

        // Set the fetched related objects if they are not null
        if (department != null) {
            employee.setDepartment(department);
        }
        if (job != null) {
            employee.setJob(job);
        }
        if (location != null) {
            employee.setLocation(location);
        }

        // Save the employee
        Employee savedEmployee = employeeRepository.save(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }
    
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
    	try {
			
			List<Employee> list= employeeRepository.findAll();
			if(list.isEmpty() || list.size()==0)
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(list,HttpStatus.OK);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			}
    }
    
    @GetMapping("/{employeeId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
		
		Optional<Employee> emp=employeeRepository.findById(id);
		
		if(emp.isPresent())
		{
			return new ResponseEntity<>(emp.get(),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    
    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee updatedEmployee) {
        try {
            // Check if the employee with the given ID exists
            if (!employeeRepository.existsById(employeeId)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Fetch the existing employee from the database
            Employee existingEmployee = employeeRepository.findById(employeeId).orElse(null);

            if (existingEmployee != null) {
                // Update the employee's properties with the new values from updatedEmployee
                existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
                existingEmployee.setAge(updatedEmployee.getAge());
                existingEmployee.setSex(updatedEmployee.getSex());

                // Fetch and set the related objects based on their IDs
                Department department = departmentRepository.findById(updatedEmployee.getDepartment().getDepartmentId()).orElse(null);
                Jobs job = jobRepository.findById(updatedEmployee.getJob().getJobId()).orElse(null);
                Location location = locationRepository.findById(updatedEmployee.getLocation().getLocationId()).orElse(null);

                if (department != null) {
                    existingEmployee.setDepartment(department);
                }
                if (job != null) {
                    existingEmployee.setJob(job);
                }
                if (location != null) {
                    existingEmployee.setLocation(location);
                }

                // Save the updated employee
                Employee updatedEmployeeResult = employeeRepository.save(existingEmployee);

                return new ResponseEntity<>(updatedEmployeeResult, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long employeeId) {
        try {
            // Check if the employee with the given ID exists
            if (!employeeRepository.existsById(employeeId)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Delete the employee
            employeeRepository.deleteById(employeeId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

