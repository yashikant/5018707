package com.employee.EmployeeManagement.repository;

import com.employee.EmployeeManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Find department by name
    Department findByName(String name);

    // Find departments by name containing a substring
    List<Department> findByNameContaining(String name);
}
