package com.passcheck.passwordcheck.controller;

import com.passcheck.passwordcheck.service.PasswordCheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PasswordCheckController.class)
public class PasswordCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PasswordCheckService passwordCheckService;

    //Point 1 - Negative Scenarios

    @Test
    public void verifyIsNullAsPassword() throws Exception {
        //setup
        final String password = null;

        //given
        given(passwordCheckService.doPasswordVerification(password)).willReturn(false);

        //verify
        this.mockMvc.perform(get("/verifyPassword?password=null"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void verifyPasswordLengthLessThan8Chars() throws Exception {
        //setup
        final String password = "Test";

        //given
        given(passwordCheckService.doPasswordVerification(password)).willReturn(false);

        //verify
        this.mockMvc.perform(get("/verifyPassword?password=Test"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void verifyPasswordNotWithOneUpperChar() throws Exception {
        //setup
        final String password = "test12345";

        //given
        given(passwordCheckService.doPasswordVerification(password)).willReturn(false);

        //verify
        this.mockMvc.perform(get("/verifyPassword?password=test12345"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }


    @Test
    public void verifyPasswordNotWithOneLowerChar() throws Exception {
        //setup
        final String password = "TEST12345";

        //given
        given(passwordCheckService.doPasswordVerification(password)).willReturn(false);

        //verify
        this.mockMvc.perform(get("/verifyPassword?password=TEST12345"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }


    @Test
    public void verifyPasswordNotWithOneNumber() throws Exception {
        //setup
        final String password = "TESTString";

        //given
        given(passwordCheckService.doPasswordVerification(password)).willReturn(false);

        //verify
        this.mockMvc.perform(get("/verifyPassword?password=TestString"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    //Point 1 - Positive check
    @Test
    public void verifyPasswordWithAllOkConditions() throws Exception {
        //setup
        final String password = "TestString1";

        //given
        given(passwordCheckService.doPasswordVerification(password)).willReturn(true);

        //verify
        this.mockMvc.perform(get("/verifyPassword?password=TestString1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    //Point 2 - Negative Scenarios

    @Test
    public void verifyPasswordWithAtLeastNot3OkConditionsOnlyCapitalLetters() throws Exception {
        //setup
        final String password = "TEST";

        //given
        given(passwordCheckService.doPasswordVerification(password)).willReturn(false);

        //verify
        this.mockMvc.perform(get("/verifyPassword?password=TEST"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void verifyPasswordWithAtLeastNot3OkConditionsOnlyNumbers() throws Exception {
        //setup
        final String password = "1234";

        //given
        given(passwordCheckService.doPasswordVerification(password)).willReturn(false);

        //verify
        this.mockMvc.perform(get("/verifyPassword?password=1234"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void verifyPasswordWithAtLeastNot3OkConditionsOnlySmallLetters() throws Exception {
        //setup
        final String password = "1234";

        //given
        given(passwordCheckService.doPasswordVerification(password)).willReturn(false);

        //verify
        this.mockMvc.perform(get("/verifyPassword?password=1234"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    //Point 2 Positive Scenario

    @Test
    public void verifyPasswordWithAtLeast3OkConditions() throws Exception {
        //setup
        final String password = "TEST123";

        //given
        given(passwordCheckService.doPasswordVerification(password)).willReturn(true);

        //verify
        this.mockMvc.perform(get("/verifyPassword?password=TEST123"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    //Point 3 Negative Scenario

    @Test
    public void verifyPasswordWithNoMustLowerCase() throws Exception {
        //setup
        final String password = "TESTING123";

        //given
        given(passwordCheckService.doPasswordVerification(password)).willReturn(true);

        //verify
        this.mockMvc.perform(get("/verifyPassword?password=TESTING123"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    //Point 3 Positive Scenario

    @Test
    public void verifyPasswordWithMustLowerCase() throws Exception {
        //setup
        final String password = "Test123";

        //given
        given(passwordCheckService.doPasswordVerification(password)).willReturn(true);

        //verify
        this.mockMvc.perform(get("/verifyPassword?password=Test123"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

}
