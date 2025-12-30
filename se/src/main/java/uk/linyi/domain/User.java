package uk.linyi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends Person implements Operator {
    private String username;
    private String password;
    private boolean isLoggedIn;

    public User(Long id, String name, String email, Integer age, String username, String password) {
        super(id, name, email, age);
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
    }

    @Override
    public String getRole() {
        return "USER";
    }

    @Override
    public boolean register(String username, String password, String email) {
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            email == null || email.trim().isEmpty()) {
            return false;
        }

        this.username = username;
        this.password = password;
        this.setEmail(email);
        this.isLoggedIn = false;
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        if (this.username != null && this.password != null &&
            this.username.equals(username) && this.password.equals(password)) {
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }
}