package com.employee.EmployeeManagement.repository;

import com.employee.EmployeeManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom query to find employees by department name using @Query annotation
    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);

    // Custom query to find employees whose names start with a certain prefix
    @Query("SELECT e FROM Employee e WHERE e.name LIKE :prefix%")
    List<Employee> findEmployeesByNameStartingWith(@Param("prefix") String prefix);
}
