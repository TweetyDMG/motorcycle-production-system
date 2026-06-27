package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponentDAOImpl implements ComponentDAO {
    @Override
    public void save(Component component) {
        String sql = "INSERT INTO components (component_id, name, material_id, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, component.getComponentID());
            stmt.setString(2, component.getName());
            stmt.setString(3, component.getMaterialID());
            stmt.setString(4, component.getStatus());
            stmt.executeUpdate();
            System.out.println("Компонент " + component.getName() + " сохранен в базе данных");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении компонента", e);
        }
    }

    @Override
    public Component findById(String componentId) {
        String sql = "SELECT * FROM components WHERE component_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, componentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Component(
                        componentId,
                        rs.getString("name"),
                        rs.getString("material_id"),
                        rs.getString("status")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске компонента", e);
        }
    }

    @Override
    public void update(Component component) {
        String sql = "UPDATE components SET name = ?, material_id = ?, status = ? WHERE component_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, component.getName());
            stmt.setString(2, component.getMaterialID());
            stmt.setString(3, component.getStatus());
            stmt.setString(4, component.getComponentID());
            stmt.executeUpdate();
            System.out.println("Компонент " + component.getName() + " обновлен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении компонента", e);
        }
    }

    @Override
    public void delete(String componentId) {
        String sql = "DELETE FROM components WHERE component_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, componentId);
            stmt.executeUpdate();
            System.out.println("Компонент с ID " + componentId + " удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении компонента", e);
        }
    }
}
