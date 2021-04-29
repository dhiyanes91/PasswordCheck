package com.passcheck.passwordcheck.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordCheckServiceTest {

    @Test
    public void verifyIsNullAsPassword() throws Exception {
        final String password = null;
        PasswordCheckService service = new PasswordCheckService();
        assertEquals(service.doPasswordVerification(password) , false);
    }

    @Test
    public void verifyPasswordLengthLessThan8Chars() throws Exception {
        final String password = "Test";
        PasswordCheckService service = new PasswordCheckService();
        assertEquals(service.doPasswordVerification(password) , false);
    }

    @Test
    public void verifyPasswordNotWithOneUpperChar() throws Exception {
        final String password = "test12345";
        PasswordCheckService service = new PasswordCheckService();
        assertEquals(service.doPasswordVerification(password) , false);
    }


    @Test
    public void verifyPasswordNotWithOneLowerChar() throws Exception {
        final String password = "TEST12345";
        PasswordCheckService service = new PasswordCheckService();
        assertEquals(service.doPasswordVerification(password) , false);
    }


    @Test
    public void verifyPasswordNotWithOneNumber() throws Exception {
        final String password = "TESTString";
        PasswordCheckService service = new PasswordCheckService();
        assertEquals(service.doPasswordVerification(password) , false);
    }

    //Point 1 - Positive check
    @Test
    public void verifyPasswordWithAllOkConditions() throws Exception {
        final String password = "TestString1";
        PasswordCheckService service = new PasswordCheckService();
        assertEquals(service.doPasswordVerification(password) , true);
    }

    //Point 2 - Negative Scenarios

    @Test
    public void verifyPasswordWithAtLeastNot3OkConditionsOnlyCapitalLetters() throws Exception {
        final String password = "TEST";
        PasswordCheckService service = new PasswordCheckService();
        assertEquals(service.doPasswordVerification(password) , false);
    }

    @Test
    public void verifyPasswordWithAtLeastNot3OkConditionsOnlyNumbers() throws Exception {
        final String password = "1234";
        PasswordCheckService service = new PasswordCheckService();
        assertEquals(service.doPasswordVerification(password) , false);
    }

    @Test
    public void verifyPasswordWithAtLeastNot3OkConditionsOnlySmallLetters() throws Exception {
        final String password = "1234";
        PasswordCheckService service = new PasswordCheckService();
        assertEquals(service.doPasswordVerification(password) , false);
    }

    //Point 2 Positive Scenario

    @Test
    public void verifyPasswordWithAtLeast3OkConditions() throws Exception {
        final String password = "TEST123";
        PasswordCheckService service = new PasswordCheckService();
        assertEquals(service.doPasswordVerification(password) , true);
    }
}
