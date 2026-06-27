package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public void save(Supplier supplier) {
        String sql = "INSERT INTO suppliers (supplier_id, material_id, name, contact_info) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, supplier.getSupplierID());
            stmt.setString(2, supplier.getMaterialID());
            stmt.setString(3, supplier.getName());
            stmt.setString(4, supplier.getContactInfo());
            stmt.executeUpdate();
            System.out.println("Поставщик " + supplier.getName() + " сохранен в базе данных");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении поставщика", e);
        }
    }

    @Override
    public Supplier findById(String supplierId) {
        String sql = "SELECT * FROM suppliers WHERE supplier_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, supplierId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Supplier(
                        supplierId,
                        rs.getString("material_id"),
                        rs.getString("name"),
                        rs.getString("contact_info")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске поставщика", e);
        }
    }

    @Override
    public void update(Supplier supplier) {
        String sql = "UPDATE suppliers SET material_id = ?, name = ?, contact_info = ? WHERE supplier_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, supplier.getMaterialID());
            stmt.setString(2, supplier.getName());
            stmt.setString(3, supplier.getContactInfo());
            stmt.setString(4, supplier.getSupplierID());
            stmt.executeUpdate();
            System.out.println("Поставщик " + supplier.getName() + " обновлен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении поставщика", e);
        }
    }

    @Override
    public void delete(String supplierId) {
        String sql = "DELETE FROM suppliers WHERE supplier_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, supplierId);
            stmt.executeUpdate();
            System.out.println("Поставщик с ID " + supplierId + " удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении поставщика", e);
        }
    }
}