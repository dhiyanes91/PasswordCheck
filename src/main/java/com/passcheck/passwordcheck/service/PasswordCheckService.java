package com.passcheck.passwordcheck.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordCheckService {

    public static Boolean checkPasswordLength (String password) {
        if (password.length() > 8) {
            return true;
        }else{
            return false;
        }
    }

    public static Boolean checkLowerChar (String password) {
        if (password.matches(".*[a-z].*")) {
            return true;
        }else{
            return false;
        }
    }

    public static Boolean checkUpperChar (String password) {
        if (password.matches(".*[A-Z].*")) {
            return true;
        }else{
            return false;
        }
    }

    public static Boolean checkNumber (String password) {
        if (password.matches(".*[0-9].*")) {
            return true;
        }else{
            return false;
        }
    }
    public Boolean doPasswordVerification(String password) throws Exception{
        Boolean isOkPassword = false;
        int flag = 0;

        if(password != null) {
            flag++;
            if(checkLowerChar(password)) {
                flag++;
                if (checkPasswordLength(password)) {
                    flag++;
                }
                if (checkUpperChar(password)) {
                    flag++;
                }
                if (checkNumber(password)) {
                    flag++;
                }
            }
        }
        if(flag >= 3) {
            isOkPassword = true;
        }
        return isOkPassword;
    }
}
