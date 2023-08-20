package com.employee.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.springboot.entity.Employee;
import org.springframework.stereotype.Repository;


@Repository
public interface IEmployeeRep extends JpaRepository<Employee, Long> {

}
