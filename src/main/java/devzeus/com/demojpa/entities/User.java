package devzeus.com.demojpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", length = 200, nullable = false)
    @NotEmpty(message = "Require username, please enter")
    private String username;

    private String password;

    @Column(name = "day_of_bird")
    private LocalDate dayOfBird;

    private String gender;

    @Column(length = 100, nullable = false, unique = true)
    @NotEmpty(message = "Require email, please enter")
    private String email;

    private int active;

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Require username, please enter") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Require username, please enter") String username) {
        this.username = username;
    }

    public LocalDate getDayOfBird() {
        return dayOfBird;
    }

    public void setDayOfBird(LocalDate dayOfBird) {
        this.dayOfBird = dayOfBird;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public @NotEmpty(message = "Require email, please enter") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Require email, please enter") String email) {
        this.email = email;
    }

    public int isActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
