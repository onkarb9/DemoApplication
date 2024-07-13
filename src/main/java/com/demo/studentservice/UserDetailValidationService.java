package com.demo.studentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailValidationService {

    @Autowired
    private Services s;

    public String validateUserDetails(String name, String email, String username, String password) {
        if (name == null || name.isEmpty() ||
            email == null || email.isEmpty() ||
            username == null || username.isEmpty() ||
            password == null || password.isEmpty()) {
            return "mandatoryfields"; // or appropriate error message
        }

        if (!name.matches("[a-zA-Z ]+")) {
            return "validname";
        }
        
        if (!username.matches("[a-zA-Z]+")) {
            return "validusername";
        }
        
        if (!email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com\\b")) {
            return "validemail";
        }

        if (s.isEmailPresent(email)) {
            return "emailpresent";
        }

        if (s.isUsernamePresent(username)) {
            return "usernamepresent";
        }

        return "valid"; // All checks passed
    }

    public String validateUserUpdate(String fieldType, String newValue) {
        switch (fieldType) {
            case "name":
                if (!newValue.matches("[a-zA-Z ]+")) {
                    return "invalidname";
                }
                break;
            case "email":
                if (!newValue.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com\\b")) {
                    return "invalidemail";
                }
                break;
            case "username":
                if (!newValue.matches("[a-zA-Z]+")) {
                    return "invalidusername";
                }
                break;
            default:
                return "invalidfieldtype";
        }
        return "valid";
    }
}
