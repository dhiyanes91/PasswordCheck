package com.passcheck.passwordcheck.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordCheckService {

    public Boolean doPasswordVerification(String password) throws Exception{
        Boolean isOkPassword = false;
        int flag = 0;

        if(password != null) {
            flag++;
                if(password.length() > 8) {
                    flag++;
                }
                if(password.matches(".*[A-Z].*")) {
                    flag++;
                }
                if(password.matches(".*[a-z].*")) {
                    flag++;
                }
                if(password.matches(".*[0-9].*")){
                    flag++;
                }

        }
        if(flag >= 3) {
            isOkPassword = true;
        }
        return isOkPassword;
    }
}
