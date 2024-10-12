package devzeus.com.demojpa.services.impl;

import devzeus.com.demojpa.daos.IUserDao;
import devzeus.com.demojpa.daos.ipml.UserDao;
import devzeus.com.demojpa.entities.User;
import devzeus.com.demojpa.services.IUserService;

import java.util.List;

public class UserService implements IUserService {
    private IUserDao userDao = new UserDao();

    @Override
    public boolean register(User user) {
        return userDao.register(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(String username) {
        userDao.delete(username);
    }
}
