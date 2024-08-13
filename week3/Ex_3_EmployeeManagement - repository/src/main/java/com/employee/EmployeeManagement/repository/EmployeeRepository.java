package com.employee.EmployeeManagement.repository;

import com.employee.EmployeeManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom query method to find employees by department name
    List<Employee> findByDepartmentName(String departmentName);

    // Custom query method to find employees by email
    Employee findByEmail(String email);
}
