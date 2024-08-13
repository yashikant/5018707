package com.employee.EmployeeManagement.repository;

import com.employee.EmployeeManagement.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Method to get paginated list of employees
    Page<Employee> findAll(Pageable pageable);
}
