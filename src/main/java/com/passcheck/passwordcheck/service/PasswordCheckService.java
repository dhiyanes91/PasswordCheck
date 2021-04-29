package com.passcheck.passwordcheck.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordCheckService {

    public Boolean doPasswordVerification(String password) {
        Boolean isOkPassword = false;
        if(password != null) {
            if(! password.isEmpty()) {
                if(password.length() > 8) {
                    if(password.matches(".*[A-Z].*")){
                        if(password.matches(".*[a-z].*")){
                            if(password.matches(".*[0-9].*")){
                                isOkPassword = true;
                            }
                        }
                    }
                }
            }
        }
        return isOkPassword;
    }
}
