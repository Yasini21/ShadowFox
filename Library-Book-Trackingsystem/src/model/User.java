package model;

public class User {

    private int userId;
    private String name;
    private String role; // STUDENT or LIBRARIAN

    // WHY: constructor to create User objects easily
    public User(int userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    // WHY: getters to access data safely
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
