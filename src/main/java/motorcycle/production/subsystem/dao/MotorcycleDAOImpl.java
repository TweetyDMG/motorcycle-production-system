package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Component;
import motorcycle.production.subsystem.model.Motorcycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotorcycleDAOImpl implements MotorcycleDAO {
    @Override
    public void save(Motorcycle motorcycle) {
        String sql = "INSERT INTO motorcycles (motorcycle_id, model, status) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, motorcycle.getMotorcycleID());
            stmt.setString(2, motorcycle.getModel());
            stmt.setString(3, motorcycle.getStatus());
            stmt.executeUpdate();
            System.out.println("Мотоцикл " + motorcycle.getModel() + " сохранен в базе данных");

            // Сохранение компонентов в motorcycle_components
            saveMotorcycleComponents(conn, motorcycle);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении мотоцикла", e);
        }
    }

    private void saveMotorcycleComponents(Connection conn, Motorcycle motorcycle) throws SQLException {
        String sql = "INSERT INTO motorcycle_components (motorcycle_id, component_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (Component component : motorcycle.getComponents()) {
                stmt.setString(1, motorcycle.getMotorcycleID());
                stmt.setString(2, component.getComponentID());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public Motorcycle findById(String motorcycleId) {
        String sql = "SELECT * FROM motorcycles WHERE motorcycle_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, motorcycleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String model = rs.getString("model");
                String status = rs.getString("status");
                Component[] components = loadComponents(conn, motorcycleId);
                return new Motorcycle(motorcycleId, model, components, status);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске мотоцикла", e);
        }
    }

    private Component[] loadComponents(Connection conn, String motorcycleId) throws SQLException {
        String sql = "SELECT c.component_id, c.name, c.material_id, c.status " +
                     "FROM components c " +
                     "JOIN motorcycle_components mc ON c.component_id = mc.component_id " +
                     "WHERE mc.motorcycle_id = ?";
        List<Component> list = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, motorcycleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Component(
                        rs.getString("component_id"),
                        rs.getString("name"),
                        rs.getString("material_id"),
                        rs.getString("status")
                ));
            }
        }
        return list.toArray(new Component[0]);
    }

    @Override
    public void update(Motorcycle motorcycle) {
        String sql = "UPDATE motorcycles SET model = ?, status = ? WHERE motorcycle_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, motorcycle.getModel());
            stmt.setString(2, motorcycle.getStatus());
            stmt.setString(3, motorcycle.getMotorcycleID());
            stmt.executeUpdate();
            System.out.println("Мотоцикл " + motorcycle.getModel() + " обновлен");

            // Обновление компонентов
            deleteMotorcycleComponents(conn, motorcycle.getMotorcycleID());
            saveMotorcycleComponents(conn, motorcycle);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении мотоцикла", e);
        }
    }

    private void deleteMotorcycleComponents(Connection conn, String motorcycleId) throws SQLException {
        String sql = "DELETE FROM motorcycle_components WHERE motorcycle_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, motorcycleId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String motorcycleId) {
        String sql = "DELETE FROM motorcycles WHERE motorcycle_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            deleteMotorcycleComponents(conn, motorcycleId);
            stmt.setString(1, motorcycleId);
            stmt.executeUpdate();
            System.out.println("Мотоцикл с ID " + motorcycleId + " удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении мотоцикла", e);
        }
    }
}
