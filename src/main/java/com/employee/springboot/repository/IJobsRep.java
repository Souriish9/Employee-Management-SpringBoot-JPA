package com.employee.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.springboot.entity.Jobs;

public interface IJobsRep extends JpaRepository<Jobs, Long> {

}
