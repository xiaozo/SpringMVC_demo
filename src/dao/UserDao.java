package dao;

import model.User;

import java.util.List;

public interface UserDao {
    public List<User> getUser(Integer id);
}
