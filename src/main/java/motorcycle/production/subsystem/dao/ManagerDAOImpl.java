package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDAOImpl implements ManagerDAO {
    @Override
    public void save(String managerId) {
        String sql = "INSERT INTO managers (manager_id) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, managerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении менеджера", e);
        }
    }

    @Override
    public void save(Manager manager){
        String sql = "INSERT INTO managers (manager_id, name, contact_info) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, manager.getManagerID());
            stmt.setString(2, manager.getName());
            stmt.setString(3, manager.getContactInfo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении менеджера", e);
        }
    }

    @Override
    public Manager findById(String managerId) {
        String sql = "SELECT * FROM managers WHERE manager_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, managerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Manager(
                        managerId,
                        rs.getString("name"),
                        rs.getString("contact_info")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске менеджера", e);
        }
    }

    @Override
    public void update(Manager manager) {
        String sql = "UPDATE managers SET name = ?, contact_info = ? WHERE manager_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, manager.getName());
            stmt.setString(2, manager.getContactInfo());
            stmt.setString(3, manager.getManagerID());
            stmt.executeUpdate();
            System.out.println("Менеджер " + manager.getName() + " обновлен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении менеджера", e);
        }
    }

    @Override
    public void delete(String managerId) {
        String sql = "DELETE FROM managers WHERE manager_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, managerId);
            stmt.executeUpdate();
            System.out.println("Менеджер с ID " + managerId + " удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении менеджера", e);
        }
    }
}