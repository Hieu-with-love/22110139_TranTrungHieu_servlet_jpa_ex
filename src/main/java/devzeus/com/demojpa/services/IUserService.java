package devzeus.com.demojpa.services;

import devzeus.com.demojpa.entities.User;

import java.util.List;

public interface IUserService {
    boolean register(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
    boolean update(User user);
    void delete(String username);
}
