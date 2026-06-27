package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialDAOImpl implements MaterialDAO {
    @Override
    public void save(Material material) {
        String sql = "INSERT INTO materials (material_id, name, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getMaterialID());
            stmt.setString(2, material.getName());
            stmt.setInt(3, material.getQuantity());
            stmt.executeUpdate();
            System.out.println("Материал " + material.getName() + " сохранен в базе данных");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении материала", e);
        }
    }

    @Override
    public Material findById(String materialId) {
        String sql = "SELECT * FROM materials WHERE material_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, materialId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Material(
                        materialId,
                        rs.getString("name"),
                        rs.getInt("quantity")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске материала", e);
        }
    }

    @Override
    public void update(Material material) {
        String sql = "UPDATE materials SET name = ?, quantity = ? WHERE material_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getName());
            stmt.setInt(2, material.getQuantity());
            stmt.setString(3, material.getMaterialID());
            stmt.executeUpdate();
            System.out.println("Материал " + material.getName() + " обновлен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении материала", e);
        }
    }

    @Override
    public void delete(String materialId) {
        String sql = "DELETE FROM materials WHERE material_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, materialId);
            stmt.executeUpdate();
            System.out.println("Материал с ID " + materialId + " удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении материала", e);
        }
    }
}