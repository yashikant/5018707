package com.employee.EmployeeManagement.entity;

import jakarta.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.findByDepartmentName", query = "SELECT e FROM Employee e WHERE e.department.name = :departmentName"),
        @NamedQuery(name = "Employee.findByNameStartingWith", query = "SELECT e FROM Employee e WHERE e.name LIKE :prefix%")
})
public class Employee {
    // Fields, getters, setters, etc.
}
