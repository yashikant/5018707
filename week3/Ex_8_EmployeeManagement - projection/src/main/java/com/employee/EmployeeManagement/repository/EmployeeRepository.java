package com.employee.EmployeeManagement.repository;

import com.employee.EmployeeManagement.dto.EmployeeDTO;
import com.employee.EmployeeManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.employee.EmployeeManagement.dto.EmployeeDTO(e.id, e.name, e.email) FROM Employee e")
    List<EmployeeDTO> findEmployeeDTOs();
}
