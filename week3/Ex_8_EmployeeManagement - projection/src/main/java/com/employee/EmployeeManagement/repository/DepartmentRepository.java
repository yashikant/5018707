package com.employee.EmployeeManagement.repository;

import com.employee.EmployeeManagement.projection.DepartmentProjection;
import com.employee.EmployeeManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d FROM Department d")
    List<DepartmentProjection> findDepartmentProjections();
}
