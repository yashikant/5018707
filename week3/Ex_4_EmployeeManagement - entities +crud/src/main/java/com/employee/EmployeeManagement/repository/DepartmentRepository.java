package com.employee.EmployeeManagement.repository;

import com.employee.EmployeeManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Custom query method to find department by name
    Department findByName(String name);
}
