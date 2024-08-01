import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementSystem {
    private List<Employee> employees;

    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee searchEmployee(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    public void traverseEmployees() {
        for (Employee employee : employees) {
            System.out.println("ID: " + employee.getEmployeeId() + ", Name: " + employee.getName() +
                               ", Position: " + employee.getPosition() + ", Salary: " + employee.getSalary());
        }
    }

    public boolean deleteEmployee(int employeeId) {
        Employee employee = searchEmployee(employeeId);
        if (employee != null) {
            employees.remove(employee);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        ems.addEmployee(new Employee(1, "John Doe", "Manager", 75000));
        ems.addEmployee(new Employee(2, "Jane Smith", "Developer", 65000));
        ems.addEmployee(new Employee(3, "Alice Johnson", "Designer", 55000));
        ems.traverseEmployees();
        Employee e = ems.searchEmployee(2);
        if (e != null) {
            System.out.println("Found: " + e.getName());
        } else {
            System.out.println("Employee not found");
        }
        if (ems.deleteEmployee(3)) {
            System.out.println("Employee deleted");
        } else {
            System.out.println("Employee not found");
        }
        ems.traverseEmployees();
    }
}
