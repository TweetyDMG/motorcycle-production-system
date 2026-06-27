package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.User;

public interface UserDAO {
    void createUser(User user);
    User findByLogin(String login);
    void save(User user);
}
