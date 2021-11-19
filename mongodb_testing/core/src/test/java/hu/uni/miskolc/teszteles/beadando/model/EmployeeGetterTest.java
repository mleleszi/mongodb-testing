package hu.uni.miskolc.teszteles.beadando.model;

import hu.uni.miskolc.teszteles.beadando.enums.Department;
import hu.uni.miskolc.teszteles.beadando.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeGetterTest {

    Employee employee;

    @BeforeEach
    public void setup(){
        this.employee = new Employee(1, "Sanyi", "Toth", "sanyi@gmail.com", "06204567844", 20, Gender.MALE, Department.DEVELOPMENT, LocalDate.of(2018, 2, 2), 3.5, false);
    }

    @Test
    public void should_GetId(){
        // given
        int expected = 1;

        // when
        int actual = employee.getId();

        // then
        assertEquals(expected, actual);
    }


    @Test
    public void should_GetFirstName(){
        // given
        String expected = "Sanyi";

        // when
        String actual = employee.getFirstName();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetLastName(){
        // given
        String expected = "Toth";

        // when
        String actual = employee.getLastName();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetEmail(){
        // given
        String expected = "sanyi@gmail.com";

        // when
        String actual = employee.getEmail();

        // then
        assertEquals(expected, actual);
    }



    @Test
    public void should_GetPhoneNumber(){
        // given
        String expected = "06204567844";

        // when
        String actual = employee.getPhoneNumber();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetAge(){
        // given
        int expected = 20;

        // when
        int actual = employee.getAge();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetGender(){
        // given
        Gender expected = Gender.MALE;

        // when
        Gender actual = employee.getGender();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetDepartment(){
        // given
        Department expected = Department.DEVELOPMENT;

        // when
        Department actual = employee.getDepartment();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetHireDate(){
        // given
        LocalDate expected = LocalDate.of(2018, 2, 2);

        // when
        LocalDate actual = employee.getHireDate();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetRating(){
        // given
        double expected = 3.5;

        // when
        double actual = employee.getRating();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_GetIsSenior(){
        // given
        boolean expected = false;

        // when
        boolean actual = employee.isSenior();

        // then
        assertEquals(expected, actual);
    }







}