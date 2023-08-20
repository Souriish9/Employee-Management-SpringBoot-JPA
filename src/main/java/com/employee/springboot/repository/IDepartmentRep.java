package com.employee.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.springboot.entity.Department;

@Repository
public interface IDepartmentRep extends JpaRepository<Department, Long> {

}
