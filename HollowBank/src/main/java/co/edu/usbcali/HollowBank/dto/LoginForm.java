package co.edu.usbcali.HollowBank.dto;

public class LoginForm {
    private String username;
    private String password;

    // Constructores, getters y setters

    public LoginForm() {
        // Constructor vacío necesario para Thymeleaf y otros frameworks
    }

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

