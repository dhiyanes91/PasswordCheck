package com.passcheck.passwordcheck.controller;

import com.passcheck.passwordcheck.service.PasswordCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PasswordCheckController {

    @Autowired
    PasswordCheckService passwordCheckService;

    @GetMapping(value="/verifyPassword")
    public @ResponseBody Boolean doPasswordVerification (@RequestParam String password) throws  Exception{
        return passwordCheckService.doPasswordVerification(password);
    }
}
