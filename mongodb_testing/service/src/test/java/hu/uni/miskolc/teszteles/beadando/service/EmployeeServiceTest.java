package hu.uni.miskolc.teszteles.beadando.service;

import hu.uni.miskolc.teszteles.beadando.dao.EmployeeDao;
import hu.uni.miskolc.teszteles.beadando.enums.Department;
import hu.uni.miskolc.teszteles.beadando.enums.Gender;
import hu.uni.miskolc.teszteles.beadando.exceptions.EmployeeAlreadyExistsException;
import hu.uni.miskolc.teszteles.beadando.exceptions.EmployeeDoesNotExistException;
import hu.uni.miskolc.teszteles.beadando.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeDao employeeDao;

    private List<Employee> employees;

    @BeforeEach
    public void setup(){
        employees = new ArrayList(Arrays.asList(
                new Employee(0, "Sanyi", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false),
                new Employee(1, "Adam", "Szucs", "adam@gmail.com", "06706587655", 30, Gender.MALE, Department.SALES, LocalDate.of(2010, 2, 1), 4.8, true),
                new Employee(2, "Timea", "Mora", "gmail@protonmail.com", "+36208765433", 41, Gender.FEMALE, Department.ACCOUNTING, LocalDate.of(2020, 1, 1), 3.2, false),
                new Employee(3, "Viktoria", "Toth", "viki@icloud.com", "0646876548", 32, Gender.FEMALE, Department.RESEARCH, LocalDate.of(2008, 1, 1), 4.1, true)
                ));
    }

    @Test
    public void should_GetAllEmployees(){
        // given
        when(employeeDao.getAllEmployees()).thenReturn(employees);
        int expected = 4;

        // when
        int actual = employeeService.getAllEmployees().size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetEmployeeById_When_EmployeeExists() throws EmployeeDoesNotExistException {
        // given
        int expected = 1;
        when(employeeDao.getEmployeeById(1)).thenReturn(employees.get(1));

        // when
        int actual = employeeService.getEmployeeById(1).getId();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_ThrowException_When_EmployeeDoesNotExist() throws EmployeeDoesNotExistException {
        // given
        Class expected = EmployeeDoesNotExistException.class;
        when(employeeDao.getEmployeeById(anyInt())).thenThrow(expected);

        // when
        Executable executable = () -> employeeService.getEmployeeById(10);

        // then
        assertThrows(expected, executable);
    }

    @Test
    public void should_CreateNewEmployee_When_EmployeeDoesNotExist() throws EmployeeAlreadyExistsException {
        // given
        Employee newEmployee = new Employee(5, "Firstname", "Lastname", "firstname@gmail.com", "06765687655", 24, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2012, 12, 12), 4.12, true);

        // when
        employeeService.createEmployee(newEmployee);

        // then
        verify(employeeDao, times(1)).createEmployee(newEmployee);
    }

    @Test
    public void should_NotCreateNewEmployee_When_EmployeeAlreadyExists() throws EmployeeAlreadyExistsException {
        // given
        Employee newEmployee = new Employee(0, "Sanyi", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false);
        Class expected = EmployeeAlreadyExistsException.class;
        doThrow(expected).when(employeeDao).createEmployee(newEmployee);

        // when
        Executable executable = () -> employeeService.createEmployee(newEmployee);

        // then
        assertThrows(expected, executable);

    }

    @Test
    public void should_UpdateEmployee_When_EmployeeExists() throws EmployeeDoesNotExistException {
        // given
        Employee employee = new Employee(0, "Sanyi2", "Toth2", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false);

        // when
        employeeService.updateEmployee(employee);

        // then
        verify(employeeDao, times(1)).updateEmployee(employee);
    }

    @Test
    public void should_NotUpdateEmployee_When_EmployeeDoesNotExist() throws EmployeeDoesNotExistException {
        // given
        Employee employee = new Employee(0, "Sanyi", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false);
        Class expected = EmployeeDoesNotExistException.class;
        doThrow(expected).when(employeeDao).updateEmployee(employee);

        // when
        Executable executable = () -> employeeService.updateEmployee(employee);

        // then
        assertThrows(expected, executable);
    }

    @Test
    public void should_DeleteEmployee_When_EmployeeExists() throws EmployeeDoesNotExistException {
        // given
        Employee employee = new Employee(0, "Sanyi", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false);

        // when
        employeeService.deleteEmployee(employee);

        // then
        verify(employeeDao, times(1)).deleteEmployee(employee);
    }

    @Test
    public void should_NotDeleteEmployee_When_EmployeeDoesNotExist() throws EmployeeDoesNotExistException {
        // given
        Employee employee = new Employee(0, "Sanyi", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false);
        Class expected = EmployeeDoesNotExistException.class;
        doThrow(expected).when(employeeDao).deleteEmployee(employee);

        // when
        Executable executable = () -> employeeService.deleteEmployee(employee);

        // then
        assertThrows(expected, executable);
    }

    @Test
    public void should_GetEmployeesWithSpecificGender(){
        // given
        when(employeeDao.getAllEmployees()).thenReturn(employees);
        Gender gender = Gender.MALE;
        int expected = 2;

        // when
        int actual = employeeService.getEmployeesWithSpecificGender(Gender.MALE).size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetEmployeesBetweenHireDates_When_WithinBounds(){
        // given

        // to cover all branches inside filter implementation
        employees.add(new Employee(0, "Sanyi", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2010, 1, 1), 4.2, false));
        employees.add(new Employee(0, "Sanyi", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2000, 1, 1), 4.2, false));

        when(employeeDao.getAllEmployees()).thenReturn(employees);
        int expected = 3;

        // when
        int actual = employeeService.getEmployeesBetweenHireDates(LocalDate.of(2005,01,01), LocalDate.of(2015,01,01)).size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_NotGetEmployeesBetweenHireDates_When_NotWithinBounds(){
        // given
        when(employeeDao.getAllEmployees()).thenReturn(employees);
        int expected = 0;

        // when
        int actual = employeeService.getEmployeesBetweenHireDates(LocalDate.of(1990,01,01), LocalDate.of(2000,01,01)).size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetEmployeesOlderThan(){
        // given
        when(employeeDao.getAllEmployees()).thenReturn(employees);
        int expected = 3;

        // when
        int actual = employeeService.getEmployeesOlderThan(25).size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetSeniorEmployees(){
        // given
        when(employeeDao.getAllEmployees()).thenReturn(employees);
        int expected = 2;

        // when
        int actual = employeeService.getSeniorEmployees().size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouuld_GetEmployeesWithSpecificEmailProvider_When_CorrectFormat(){
        // given
        when(employeeDao.getAllEmployees()).thenReturn(employees);
        String emailProvider = "gmail";
        int expected = 2;

        // when
        int actual = employeeService.getEmployeesWithSpecificEmailProvider(emailProvider).size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetEarliestEmployee(){
        // given
        when(employeeDao.getAllEmployees()).thenReturn(employees);
        Employee expected = employees.get(3);

        // when
        Employee actual = employeeService.getEarliestEmployee();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void EmployeeAlreadyExistsExceptionTest(){
        try{
            throw new EmployeeAlreadyExistsException();
        }catch(Exception e){}
    }

    @Test
    public void EmployeeDoesNotExistsExceptionTest(){
        try{
            throw new EmployeeDoesNotExistException();
        }catch(Exception e){}
    }







}