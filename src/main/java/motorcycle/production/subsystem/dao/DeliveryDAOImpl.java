package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Delivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAOImpl implements DeliveryDAO {
    @Override
    public void save(Delivery delivery) {
        String sql = "INSERT INTO deliveries (delivery_id, motorcycle_id, customer_id, destination, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, delivery.getDeliveryID());
            stmt.setString(2, delivery.getMotorcycleID());
            stmt.setString(3, delivery.getCustomerID());
            stmt.setString(4, delivery.getDestination());
            stmt.setString(5, delivery.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка при сохранении доставки: " + e.getMessage());
        }
    }

    @Override
    public Delivery findById(String deliveryId) {
        String sql = "SELECT * FROM deliveries WHERE delivery_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, deliveryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Delivery(
                        rs.getString("delivery_id"),
                        rs.getString("motorcycle_id"),
                        rs.getString("customer_id"),
                        rs.getString("destination"),
                        rs.getString("status")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка при поиске доставки: " + e.getMessage());
        }
    }

    @Override
    public void update(Delivery delivery) {
        String sql = "UPDATE deliveries SET motorcycle_id = ?, customer_id = ?, destination = ?, status = ? WHERE delivery_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, delivery.getMotorcycleID());
            stmt.setString(2, delivery.getCustomerID());
            stmt.setString(3, delivery.getDestination());
            stmt.setString(4, delivery.getStatus());
            stmt.setString(5, delivery.getDeliveryID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка при обновлении доставки: " + e.getMessage());
        }
    }

    @Override
    public void delete(String deliveryId) {
        String sql = "DELETE FROM deliveries WHERE delivery_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, deliveryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка при удалении доставки: " + e.getMessage());
        }
    }

    @Override
    public List<Delivery> findAll() {
        String sql = "SELECT * FROM deliveries";
        List<Delivery> deliveries = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                deliveries.add(new Delivery(
                        rs.getString("delivery_id"),
                        rs.getString("motorcycle_id"),
                        rs.getString("customer_id"),
                        rs.getString("destination"),
                        rs.getString("status")
                ));
            }
            return deliveries;
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка при получении всех доставок: " + e.getMessage());
        }
    }
}