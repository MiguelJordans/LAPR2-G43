package app.domain.model;

import app.controller.App;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeTest {
    Role role = new Role("1", "Medical Lab Technician");

    @Test
    public void RegisterEmployee() {
        //Arrange + Act
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "something@isep.com", "111111111111111111", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeeBlank() {
        //Arrange + Act
        Employee employee = new Employee(null, null, null, null, null, null, null);
    }

    @Test
    public void RegisterEmployeeNameLimits() {
        //Arrange + Act
        Employee employee = new Employee("AB00001", "AlBinoz", "AtuaTerra", "91234567811", "something@isep.com", "111111111111111111", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeeNameOutLimits() {
        //Arrange + Act
        Employee employee = new Employee("AB00001", "AlBino.-", "AtuaTerra", "91234567811", "something@isep.com", "111111111111111111", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeeNameWithNum() {
        //Arrange + Act
        Employee employee = new Employee("B00001", "Bino1", "AtuaTerra", "91234567811", "something@isep.com", "11111111111111111", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateEmployeeNameBlank() {
        //Arrange + Act
        Employee employee = new Employee("00001", "", "AtuaTerra", "91234567811", "something@isep.com", "11111111111111111", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateEmployeeAddressBlank() {
        //Arrange + Act
        Employee employee = new Employee("B00001", "Bino", "", "91234567811", "something@isep.com", "11111111111111111", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeePhoneNumberWithLetter() {
        //Arrange + Act
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567A1", "something@isep.com", "11111111111111111", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeePhoneNumberOutLimits() {
        //Arrange + Act
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567-1", "something@isep.com", "11111111111111111", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateEmployeePhoneNumberBlank() {
        //Arrange + Act
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "", "something@isep.com", "11111111111111111", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateEmployeeEmailBlank() {
        //Arrange + Act
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "", "11111111111111111", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateEmployeeEmailWrongFormat() {
        //Arrange + Act
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "bino@fusivel", "11111111111111111", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateEmployeeSOCBlank() {
        //Arrange + Act
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "something@isep.com", "", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeeSOCWithLetter() {
        //Arrange + Act
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "something@isep.com", "11111111111111111A", role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeeSOCOutLimits() {
        //Arrange + Act
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "something@isep.com", "11111111111111111-", role);
    }

    @Test
    public void toStringTest() {
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "something@isep.com", "111111111111111111", role);
        String expected = "Employee: ID=B00001, name=Bino, address=AtuaTerra, phonenumber=91234567811, email=something@isep.com, soc=111111111111111111, Role=" + role.toString();
        String actual = employee.toString();
        Assert.assertEquals(expected, actual);
    }

//    @Test(expected = AssertionError.class)
//    public void addUserWithRole(){
//        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "something@isep.com", "111111111111111111", role);
//        Assert.assertTrue(employee.addUserWithRole(App.getInstance().getCompany()));
//
//    }
//    @Test
//    public void addUserWithRole2(){
//        Role role = new Role("3", "LaboratoryCoordinator");
//        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "something@isep.com", "111111111111111111", role);
//        Assert.assertTrue(employee.addUserWithRole(App.getInstance().getCompany()));
//
//    }
//    @Test(expected = AssertionError.class)
//    public void addUserWithRole3(){
//        Role role = new Role("2", "Clinical Chemistry Technologist");
//        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "something@isep.com", "111111111111111111", role);
//        Assert.assertTrue(employee.addUserWithRole(App.getInstance().getCompany()));
//
//    }
//    @Test(expected = AssertionError.class)
//    public void addUserWithRol4(){
//        Role role = new Role("3", "Receptionist");
//        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "something@isep.com", "111111111111111111", role);
//        Assert.assertTrue(employee.addUserWithRole(App.getInstance().getCompany()));
//
//    }

//    @Test(expected = IllegalArgumentException.class)
//    public void addUserWithRol5(){
//        Role role = new Role("4", "SpecialistDoctor");
//        SpecialistDoctor specialistDoctor = new SpecialistDoctor("B00001", "Bino", "AtuaTerra", "91234567811", "123445g@isep.com", "111111111111111111","1234567" ,role);
//        Assert.assertTrue(specialistDoctor.addUserWithRole(App.getInstance().getCompany()));
//
//    }

    @Test
    public void getEmail(){
        Role role = new Role("3", "Receptionist");
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "something@isep.com", "111111111111111111", role);
        Assert.assertNotNull(employee.getEmail());
    }

    @Test
    public void getPhoneNumber(){
        Role role = new Role("3", "Receptionist");
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "91234567811", "something@isep.com", "111111111111111111", role);
        Assert.assertNotNull(employee.getPhonenumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidPhoneNumber(){
        Role role = new Role("3", "Receptionist");
        Employee employee = new Employee("B00001", "Bino", "AtuaTerra", "aaaaaaaaaaa", "something@isep.com", "111111111111111111", role);

    }

}