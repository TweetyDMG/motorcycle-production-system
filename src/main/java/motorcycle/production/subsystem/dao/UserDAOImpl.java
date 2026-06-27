package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO users (user_id, login, password, role, subrole) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getSubrole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка при создании пользователя: " + e.getMessage());
        }
    }

    @Override
    public User findByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("user_id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("subrole")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка при поиске пользователя: " + e.getMessage());
        }
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (user_id, login, password, role, subrole) VALUES (?, ?, ?, ?, ?) " +
                     "ON CONFLICT (login) DO UPDATE SET password = EXCLUDED.password, role = EXCLUDED.role, subrole = EXCLUDED.subrole";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getSubrole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка при сохранении пользователя: " + e.getMessage());
        }
    }
}