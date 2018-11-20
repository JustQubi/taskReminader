package pl.justqubi.taskReminader.models.forms;

import lombok.Data;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterForm {
    @Pattern(regexp = "[a-zA-Z0-9]{3,20}")
    private String login;
    @Size(min = 3, max = 50)
    private String password;
    private String passwordTwo;
    private String name;
    private String surname;
    @Pattern(regexp = "\\d{2}-\\d{3}")
    private String zipCode;
    private String city;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPasswordTwo() {
        return passwordTwo;
    }

    public void setPasswordTwo(String passwordTwo) {
        this.passwordTwo = passwordTwo;
    }
}
