package hu.uni.miskolc.teszteles.beadando.model;

import hu.uni.miskolc.teszteles.beadando.enums.Department;
import hu.uni.miskolc.teszteles.beadando.enums.Gender;
import hu.uni.miskolc.teszteles.beadando.exceptions.*;

import java.time.LocalDate;

public class Employee {

    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;  // only works with hungarian phone numbers
    protected int age;
    protected Gender gender;
    protected Department department;
    protected LocalDate hireDate;
    protected double rating;
    protected boolean isSenior;

    public Employee(int id, String firstName, String lastName, String email, String phoneNumber, int age, Gender gender, Department department, LocalDate hireDate, double rating, boolean isSenior) {
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

    public Employee(){}

    public int getId() {
        return id;
    }

    public void setId(int id) throws InvalidIdException {
        if(id < 0) throw new InvalidIdException();
        this.id =  id;
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

    public void setEmail(String email) throws InvalidEmailException {
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if(!email.matches(regex)) throw new InvalidEmailException();
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // only works with hungarian phone numbers
    public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        String regex = "((?:\\+?3|0)6)(?:-|\\()?(\\d{1,2})(?:-|\\))?(\\d{3})-?(\\d{3,4})";
        if(!phoneNumber.matches(regex)) throw new InvalidPhoneNumberException();
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws AgeNotWithinBoundsException {
        if(age < 18 || age > 65) throw new AgeNotWithinBoundsException();
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

    public LocalDate getHireDate(){
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) throws HireDateException  {
        if(LocalDate.now().isBefore(hireDate)) throw new HireDateException();
        this.hireDate = hireDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) throws RatingNotWithinBoundsException {
        if(rating < 0 || rating > 5) throw new RatingNotWithinBoundsException();
        this.rating = rating;
    }

    public boolean isSenior() {
        return isSenior;
    }

    public void setSenior(boolean senior) {
        isSenior = senior;
    }
}
