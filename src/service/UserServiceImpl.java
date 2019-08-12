package service;

import java.util.List;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userdao;
    @Override
    public List<User> getUserService(int id) {

        List<User> username = userdao.getUser(id);
        return username;
    }

}

