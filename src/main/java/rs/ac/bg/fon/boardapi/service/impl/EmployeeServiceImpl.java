package rs.ac.bg.fon.boardapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.boardapi.exception.custom.EmployeeNotFoundException;
import rs.ac.bg.fon.boardapi.model.Employee;
import rs.ac.bg.fon.boardapi.repository.EmployeeRepository;
import rs.ac.bg.fon.boardapi.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
    }
}
