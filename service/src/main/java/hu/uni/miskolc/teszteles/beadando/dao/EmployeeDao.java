package hu.uni.miskolc.teszteles.beadando.dao;

import hu.uni.miskolc.teszteles.beadando.exceptions.EmployeeAlreadyExistsException;
import hu.uni.miskolc.teszteles.beadando.exceptions.EmployeeDoesNotExistException;
import hu.uni.miskolc.teszteles.beadando.model.Employee;

import java.util.Collection;
public interface EmployeeDao {

    Collection<Employee> getAllEmployees();
    Employee getEmployeeById(int id) throws EmployeeDoesNotExistException;
    void createEmployee(Employee employee) throws EmployeeAlreadyExistsException;
    void updateEmployee(Employee employee) throws EmployeeDoesNotExistException;
    void deleteEmployee(Employee employee) throws EmployeeDoesNotExistException;
    void deleteAllEmployees();
}
