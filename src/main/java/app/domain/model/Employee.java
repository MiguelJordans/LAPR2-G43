package app.domain.model;

import app.domain.shared.Constants;
import auth.AuthFacade;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Class that represents an Employee
 */
public class Employee {
    private String name;
    private String address;
    private String phonenumber;
    private String email;
    private String SOC;
    private Role role;
    private String employeeID;

    /**
     *  Constructor of the Employee, it calls methods in order to validate the parameters
     * @param employeeID unique ID generated by the system for the Employee
     * @param name name of the Employee
     * @param address address of the Employee
     * @param phonenumber Phone number of the Employee
     * @param email email of the Employee
     * @param SOC standard occupation code of the Employee
     * @param role role of the employee
     */
    public Employee(String employeeID, String name, String address, String phonenumber, String email, String SOC, Role role) {
        checkNameRules(name);
        checkAddressRules(address);
        checkPhoneNumberRules(phonenumber);
        checkEmailRules(email);
        checkSOCRules(SOC);
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
        this.SOC = SOC;
        this.role = role;
        this.employeeID = employeeID;

    }



    /**
     * Checks if the string that is received meets the requirements of the name, if not throws Exceptions. Replace all the capital letters to lower letters, cut off all the special characters and spaces and checks if all the remain characters are letters
     *
     * @param name String representing the name of the employee that is going to be checked
     */
    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");

        name = name.toLowerCase();

        name = Normalizer.normalize(name, Normalizer.Form.NFD);
        name = name.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        name = name.replaceAll(" ", "");

        char[] charArray = name.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= 'a' && c <= 'z')) {
                throw new IllegalArgumentException("Name only accepts letters");
            }
        }
    }

    /**
     * Checks if the string that is received meets the requirements of the address, if not throws Exceptions
     * @param address address of the employee
     */
    private void checkAddressRules(String address) {
        if (StringUtils.isBlank(address))
            throw new IllegalArgumentException("Address cannot be blank.");
    }

    /**
     * Checks if the string that is received meets the requirements of the phone number, if not throws Exceptions
     *
     * @param phoneNumber unique phone number that belongs to client
     */
    private void checkPhoneNumberRules(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber))
            throw new IllegalArgumentException("Phonenumber cannot be blank.");
        if (phoneNumber.length() != 11)
            throw new IllegalArgumentException("Phonenumber must have 11 chars.");

        char[] charArray = phoneNumber.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= '0' && c <= '9')) {
                throw new IllegalArgumentException("Phonenumber only accepts numbers");
            }
        }
    }




    /**
     * This methode checks if email is correct.
     * Retrieved by professor Paulo Maio code of template of the project.
     * @author Paulo Maio <pam@isep.ipp.pt>
      * @param email
     */

    private void checkEmailRules(String email) {

        if (StringUtils.isBlank(email))
            throw new IllegalArgumentException("Email cannot be blank.");

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if(!pat.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid Email.");
        }
    }




    /**
     * Checks if the string that is received meets the requirements of the Standard Occupation Number, if not throws Exceptions
     *
     * @param SOC unique phone number that belongs to client
     */
    private void checkSOCRules(String SOC) {
        if (StringUtils.isBlank(SOC))
            throw new IllegalArgumentException("SOC cannot be blank.");

        char[] charArray = SOC.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= '0' && c <= '9')) {
                throw new IllegalArgumentException("SOC only accepts numbers");
            }
        }
    }

    /**
     * This method generates a new random password with alphanumeric characters using the class RandomStringUtils
     *
     * @return random string representing the employee password
     */
    String getPassword() {
        int lenght = 10;

        return RandomStringUtils.randomAlphanumeric(lenght);
    }

    /**
     * Adds a new user to the system with the respective role using the getPassword method to create the user's password
     * @param company instance of company class in order to be able to get the AuthFacade class that is associated with the system
     * @return a boolean value representing the success of the operation
     */
    public boolean addUserWithRole(Company company) {

        boolean success = false;
        String password = getPassword();
        AuthFacade authFacade = company.getAuthFacade();

        if (role.equals("Clinical Chemistry Technologist")) {
            success = authFacade.addUserWithRole(this.name, this.email, getPassword(), Constants.ROLE_CLINICALCHEMISTRYTECHNOLOGIST);
        }

        if (role.equals("Medical Lab Technician")) {
            success = authFacade.addUserWithRole(this.name, this.email, getPassword(), Constants.ROLE_MEDICALLABTECHNICIIAN);
        }

        if (role.equals("LaboratoryCoordinator"))
            success = authFacade.addUserWithRole(this.name, this.email, getPassword(), Constants.ROLE_LABORATORYCOORDINATOR);

        if (role.equals("Receptionist")) {
            success = authFacade.addUserWithRole(this.name, this.email, getPassword(), Constants.ROLE_RECEPTIONIST);
        }

        if (success){
            Email mail = new Email(this.email,getPassword());

        }
        return success;
    }


    /**
     * @return A string with the format "Employee: ID=  employeeID, name= name, address=  address, phonenumber= phonenumber, email= email, SOC= SOC, Role=  role"
     */
    @Override
    public String toString() {
        return "Employee:" +
                " ID=" + this.employeeID +
                ", name=" + name +
                ", address=" + address +
                ", phonenumber=" + phonenumber +
                ", email=" + email +
                ", SOC=" + SOC +
                ", Role="+ role ;

    }
    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }



}
