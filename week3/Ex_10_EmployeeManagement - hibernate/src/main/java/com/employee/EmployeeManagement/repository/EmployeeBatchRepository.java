import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeBatchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void batchInsertEmployees(List<Employee> employees) {
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = session.beginTransaction();

        int batchSize = 50;
        for (int i = 0; i < employees.size(); i++) {
            session.save(employees.get(i));
            if (i % batchSize == 0) { // Same as batchSize
                session.flush();
                session.clear();
            }
        }

        transaction.commit();
    }
}
