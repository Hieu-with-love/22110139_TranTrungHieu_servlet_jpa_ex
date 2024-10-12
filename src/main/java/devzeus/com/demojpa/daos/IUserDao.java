package devzeus.com.demojpa.daos;

import devzeus.com.demojpa.entities.User;

import java.util.List;

public interface IUserDao {
    boolean register(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
    boolean update(User user);
    void delete(String username);
}
