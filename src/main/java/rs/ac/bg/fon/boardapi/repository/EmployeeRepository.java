package rs.ac.bg.fon.boardapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.boardapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
