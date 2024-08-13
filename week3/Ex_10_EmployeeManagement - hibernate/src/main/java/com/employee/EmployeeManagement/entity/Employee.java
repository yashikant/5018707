import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Formula;

@Entity
public class Employee {

    @Id
    private String id;

    private int salary;

    @Formula("salary * 0.1")
    private double bonus;

    // Getters and Setters
}
