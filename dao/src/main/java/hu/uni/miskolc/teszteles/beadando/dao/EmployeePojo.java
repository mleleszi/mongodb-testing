package hu.uni.miskolc.teszteles.beadando.dao;

import hu.uni.miskolc.teszteles.beadando.enums.Department;
import hu.uni.miskolc.teszteles.beadando.enums.Gender;
import org.bson.codecs.pojo.annotations.BsonId;

import java.time.LocalDate;

public class EmployeePojo {
    @BsonId
    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;  // only works with hungarian phone numbers
    public int age;
    public Gender gender;
    public Department department;
    public LocalDate hireDate;
    public double rating;
    public boolean isSenior;

    public EmployeePojo() {
    }

    public EmployeePojo(int id, String firstName, String lastName, String email, String phoneNumber, int age, Gender gender, Department department, LocalDate hireDate, double rating, boolean isSenior) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.hireDate = hireDate;
        this.rating = rating;
        this.isSenior = isSenior;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isSenior() {
        return isSenior;
    }

    public void setSenior(boolean senior) {
        isSenior = senior;
    }
}
