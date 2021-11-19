package hu.uni.miskolc.teszteles.beadando.dao;

import hu.uni.miskolc.teszteles.beadando.model.Employee;

public class EmployeePojoConverter {
    public static EmployeePojo employeeToPojo(Employee employee){
        return new EmployeePojo(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhoneNumber(), employee.getAge(), employee.getGender(), employee.getDepartment(), employee.getHireDate(), employee.getRating(), employee.isSenior());
    }

    public static Employee pojoToEmployee(EmployeePojo employee){
        return new Employee(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhoneNumber(), employee.getAge(), employee.getGender(), employee.getDepartment(), employee.getHireDate(), employee.getRating(), employee.isSenior());
    }
}
