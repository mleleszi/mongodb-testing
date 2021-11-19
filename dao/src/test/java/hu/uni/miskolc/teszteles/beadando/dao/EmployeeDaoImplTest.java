package hu.uni.miskolc.teszteles.beadando.dao;

import hu.uni.miskolc.teszteles.beadando.enums.Department;
import hu.uni.miskolc.teszteles.beadando.enums.Gender;
import hu.uni.miskolc.teszteles.beadando.exceptions.EmployeeAlreadyExistsException;
import hu.uni.miskolc.teszteles.beadando.exceptions.EmployeeDoesNotExistException;
import hu.uni.miskolc.teszteles.beadando.model.Employee;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoImplTest {

    private EmployeeDao dao;

    @BeforeEach
    public void setup(){
        dao = new EmployeeDaoImpl("mongodb+srv://admin:admin@cluster0.71ark.mongodb.net/myFirstDatabase?retryWrites=true&w=majority", "sample", "sampleCollection");

        List<Employee> employees = new ArrayList(Arrays.asList(
                new Employee(0, "Sanyi", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false),
                new Employee(1, "Adam", "Szucs", "adam@gmail.com", "06706587655", 30, Gender.MALE, Department.SALES, LocalDate.of(2010, 2, 1), 4.8, true),
                new Employee(2, "Timea", "Mora", "gmail@protonmail.com", "+36208765433", 41, Gender.FEMALE, Department.ACCOUNTING, LocalDate.of(2020, 1, 1), 3.2, false),
                new Employee(3, "Viktoria", "Toth", "viki@icloud.com", "0646876548", 32, Gender.FEMALE, Department.RESEARCH, LocalDate.of(2008, 1, 1), 4.1, true)
        ));

        employees.forEach((e) -> {
            try {
                dao.createEmployee(e);
            } catch (EmployeeAlreadyExistsException ex) {
                ex.printStackTrace();
            }
        });
    }

    @AfterEach
    public void reset(){
        dao.deleteAllEmployees();
    }

    @Test
    public void should_GetAllEmployees(){
        // given
        int expected = 4;

        // when
        int actual = dao.getAllEmployees().size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetEmployeeById_When_EmployeeExists() throws EmployeeDoesNotExistException {
        // given
        int expected = 1;

        // when
        int actual = dao.getEmployeeById(1).getId();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_NotGetEmployeeById_When_EmployeeDoesNotExist() {
        // given
        Class expected = EmployeeDoesNotExistException.class;

        // when
        Executable executable= () -> dao.getEmployeeById(6);

        // then
        assertThrows(expected, executable);
    }

    @Test
    public void should_CreateEmployee_When_EmployeeDoesNotExist() throws EmployeeAlreadyExistsException {
        // given
        int expected = 5;

        // when
        dao.createEmployee(new Employee(4, "Sanyi", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false));
        int actual = dao.getAllEmployees().size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_NotCreateEmployee_When_EmployeeAlreadyExists() {
        // given
        Class expected = EmployeeAlreadyExistsException.class;
        Employee employee = new Employee(0, "Sanyi", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false);

        // when
        Executable executable= () -> dao.createEmployee(employee);

        // then
        assertThrows(expected, executable);
    }

    @Test
    public void should_UpdateEmployee_When_EmployeeExists() throws EmployeeDoesNotExistException {
        // given
        String expected = "ModifiedName";
        Employee employee = new Employee(0, expected, "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false);

        // when
        dao.updateEmployee(employee);
        String actual = dao.getEmployeeById(0).getFirstName();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_NotUpdateEmployee_When_EmployeeDoesNotExist(){
        // given
        Class expected = EmployeeDoesNotExistException.class;
        Employee employee = new Employee(10, "Peti", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false);

        // when
        Executable executable= () -> dao.updateEmployee(employee);

        // then
        assertThrows(expected, executable);
    }

    @Test
    public void should_DeleteEmployee_When_EmployeeExists() throws EmployeeDoesNotExistException {
        // given
        int expected = 3;
        Employee employee = new Employee(0, "Sanyi", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false);

        // when
        dao.deleteEmployee(employee);
        int actual = dao.getAllEmployees().size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_NotDeleteEmployee_When_EmployeeDoesNotExist(){
        // given
        Class expected = EmployeeDoesNotExistException.class;
        Employee employee = new Employee(10, "Peti", "Toth", "sanyi@gmail.com", "06208765433", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 1, 1), 4.2, false);

        // when
        Executable executable = () -> dao.deleteEmployee(employee);

        // then
        assertThrows(expected, executable);
    }

    @Test
    public void should_DeleteAllEmployees(){
        // given
        int expected = 0;

        // when
        dao.deleteAllEmployees();
        int actual = dao.getAllEmployees().size();

        // then
        assertEquals(expected, actual);
    }
}