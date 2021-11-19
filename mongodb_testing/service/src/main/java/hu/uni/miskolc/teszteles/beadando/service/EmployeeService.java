package hu.uni.miskolc.teszteles.beadando.service;

import hu.uni.miskolc.teszteles.beadando.dao.EmployeeDao;
import hu.uni.miskolc.teszteles.beadando.enums.Gender;
import hu.uni.miskolc.teszteles.beadando.exceptions.EmployeeAlreadyExistsException;
import hu.uni.miskolc.teszteles.beadando.exceptions.EmployeeDoesNotExistException;
import hu.uni.miskolc.teszteles.beadando.model.Employee;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Collection<Employee> getAllEmployees(){
        return employeeDao.getAllEmployees();
    }

    public Employee getEmployeeById(int id) throws EmployeeDoesNotExistException {
        return employeeDao.getEmployeeById(id);
    }

    public void createEmployee(Employee employee) throws EmployeeAlreadyExistsException {
        employeeDao.createEmployee(employee);
    }

    public void updateEmployee(Employee employee) throws EmployeeDoesNotExistException {
        employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(Employee employee) throws EmployeeDoesNotExistException {
        employeeDao.deleteEmployee(employee);
    }

    public Collection<Employee> getEmployeesWithSpecificGender(Gender gender){
        return getAllEmployees().stream()
                .filter(e -> e.getGender() == gender)
                .collect(Collectors.toList());
    }

    public Collection<Employee> getEmployeesBetweenHireDates(LocalDate from, LocalDate to){
        return getAllEmployees().stream()
                .filter(e -> e.getHireDate().isAfter(from) && e.getHireDate().isBefore(to))
                .collect(Collectors.toList());
    }

    public Collection<Employee> getEmployeesOlderThan(int age){
        return getAllEmployees().stream()
                .filter(e -> e.getAge() > age)
                .collect(Collectors.toList());
    }

    public Collection<Employee> getSeniorEmployees(){
        return getAllEmployees().stream()
                .filter(Employee::isSenior)
                .collect(Collectors.toList());
    }

    public Collection<Employee> getEmployeesWithSpecificEmailProvider(String emailProvider){
        return getAllEmployees().stream()
                .filter(e -> e.getEmail().contains("@" + emailProvider))
                .collect(Collectors.toList());
    }

    public Employee getEarliestEmployee(){
        return getAllEmployees().stream()
                .min(Comparator.comparing(e -> e.getHireDate()))
                .get();
    }







}
