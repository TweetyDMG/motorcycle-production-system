package motorcycle.production.subsystem.model;

import java.util.UUID;

public class User {
    private String userId;
    private String login;
    private String password;
    private String role;
    private String subrole;

    public User(String login, String password, String role, String subrole) {
        this.userId = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.role = role;
        this.subrole = subrole;
    }

    public User(String userId, String login, String password, String role, String subrole) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.role = role;
        this.subrole = subrole;
    }

    public String getUserId() { return userId; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getSubrole() { return subrole; }

    public void setUserId(String userId) { this.userId = userId; }
    public void setLogin(String login) { this.login = login; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
    public void setSubrole(String subrole) { this.subrole = subrole; }
}