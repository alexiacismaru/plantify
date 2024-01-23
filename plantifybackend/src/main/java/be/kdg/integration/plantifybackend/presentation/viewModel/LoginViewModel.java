package be.kdg.integration.plantifybackend.presentation.viewModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * ViewModel for storing and BeanValidating input from the login.html form
 */
public class LoginViewModel {

    /**
     * Email of user, input needs to be in an email format and not empty
     */
    @NotEmpty(message = "Email can't be empty!")
    @Email(regexp = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$",
            message = "Not a valid email or password!")
    private String email;

    /**
     * Password of a user, input needs to be at least 5 characters long and not empty
     */
    @NotEmpty(message = "Password can't be empty!")
    @Size(min=5, message = "and must be at least 5 characters long!")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginViewModel{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
