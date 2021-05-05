package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {


    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeeNameWithNum() {
        //Arrange + Act
        Employee employee = new Employee("Bino1","AtuaTerra", "912345678","something@isep.com","11111111111111111","ok"  );
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeePhoneNumberWithLetter() {
        //Arrange + Act
        Employee employee = new Employee("Bino","AtuaTerra", "91234567A","something@isep.com","11111111111111111","ok"  );
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeeSOCWithLetter() {
        //Arrange + Act
        Employee employee = new Employee("Bino","AtuaTerra", "912345678","something@isep.com","11111111111111111A","ok"  );
    }

}