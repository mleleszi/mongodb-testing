package hu.uni.miskolc.teszteles.beadando.model;

import hu.uni.miskolc.teszteles.beadando.enums.Department;
import hu.uni.miskolc.teszteles.beadando.enums.Gender;
import hu.uni.miskolc.teszteles.beadando.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeSetterTest {

    Employee employee;

    @BeforeEach
    public void setup(){
        this.employee = new Employee();
    }

    @Test
    public void should_SetId_When_OK() throws InvalidIdException {
        // given
        int expected = 1;

        // when
        employee.setId(1);
        int actual = employee.getId();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_ThrowException_When_IdLessThan0(){
        // given
        int id = -2;
        Class expected = InvalidIdException.class;

        // when
        Executable executable = () -> employee.setId(id);

        // then
        assertThrows(expected, executable);
    }

    @Test
    public void should_SetFirstName(){
        // given
        String expected = "Sanyi";

        // when
        employee.setFirstName("Sanyi");
        String actual = employee.getFirstName();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_SetLastName(){
        // given
        String expected = "Toth";

        // when
        employee.setLastName("Toth");
        String actual = employee.getLastName();

        // then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"sanyi@gmail.com", "a@freemail.hu"})
    public void should_SetEmail_When_OKFormat(String email) throws InvalidEmailException {
        // given
        String expected = email;

        // when
        employee.setEmail(email);
        String actual = employee.getEmail();

        // then
        assertEquals(expected, actual);

    }

    @ParameterizedTest
    @ValueSource(strings = {"gmail.com", "@gmail.com", "sanyi@.com", "sanyi@", "sanyi@gmail", "sanyi@gmailcom", "sanyigmail", ""})
    public void should_ThrowException_When_EmailIncorrectFormat(String email) {
        // given
        Class expected = InvalidEmailException.class;

        // when
        Executable executable = () -> employee.setEmail(email);

        // then
        assertThrows(expected, executable);

    }


    @ParameterizedTest
    @ValueSource(strings = {"06205643788", "+36707659876", "0646765897", "+3646765778"})
    public void should_SetPhoneNumber_When_OKFormat(String phoneNumber) throws InvalidPhoneNumberException {
        // given
        String expected = phoneNumber;

        // when
        employee.setPhoneNumber(phoneNumber);
        String actual = employee.getPhoneNumber();

        // then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"notaphonenumber", "123123123123123", "123", "+44207686544", "0980123123", "0620 676 12 33", ""})
    public void should_ThrowException_When_PhoneNumberIncorrectFormat(String phoneNumber) {
        // given
        Class expected = InvalidPhoneNumberException.class;

        // when
        Executable executable = () -> employee.setPhoneNumber(phoneNumber);

        // then
        assertThrows(expected, executable);
    }



    @Test
    public void should_SetAge_When_AgeWithinBounds() throws AgeNotWithinBoundsException {
        // given
        int expected = 20;

        // when
        employee.setAge(20);
        int actual = employee.getAge();
        
        // then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {17, 66})
    public void should_ThrowException_When_AgeNotWithinBounds(int age) {
        // given
        Class expected = AgeNotWithinBoundsException.class;

        // when
        Executable executable = () -> employee.setAge(age);

        // then
        assertThrows(expected, executable);
    }

    @Test
    public void should_SetGender(){
        // given
        Gender expected = Gender.MALE;

        // when
        employee.setGender(Gender.MALE);
        Gender actual = employee.getGender();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_SetDepartment(){
        // given
        Department expected = Department.DEVELOPMENT;

        // when
        employee.setDepartment(Department.DEVELOPMENT);
        Department actual = employee.getDepartment();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_SetHireDate_When_DateOK() throws HireDateException {
        // given
        LocalDate expected = LocalDate.of(2018, 01, 01);

        // when
        employee.setHireDate(LocalDate.of(2018, 01, 01));
        LocalDate actual = employee.getHireDate();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_ThrowException_When_DateLaterThanToday(){
        // given
        LocalDate date = LocalDate.of(2022, 10, 10);
        Class expected = HireDateException.class;

        // when
        Executable executable = () -> employee.setHireDate(date);

        // then
        assertThrows(expected, executable);
    }

    @Test
    public void should_SetRating_When_RatingWithinBounds() throws RatingNotWithinBoundsException {
        // given
        double expected = 3.2;

        // when
        employee.setRating(3.2);
        double actual = employee.getRating();

        // then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.2, 6.5})
    public void should_ThrowException_When_RatingNotWithinBounds(double rating){
        // given
        Class expected = RatingNotWithinBoundsException.class;

        // when
        Executable executable = () -> employee.setRating(rating);

        // then
        assertThrows(expected, executable);

    }

    @Test
    public void should_SetSenior(){
        // given
        boolean expected = false;

        // when
        employee.setSenior(false);
        boolean actual = employee.isSenior();

        // then
        assertEquals(expected, actual);
    }








}