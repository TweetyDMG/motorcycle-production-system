package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAOImpl implements ClientDAO {
    @Override
    public void save(Client client) {
        String sql = "INSERT INTO clients (customer_id, name, contact_info) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getCustomerID());
            stmt.setString(2, client.getName());
            stmt.setString(3, client.getContactInfo());
            stmt.executeUpdate();
            System.out.println("Клиент " + client.getName() + " сохранен в базе данных");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении клиента", e);
        }
    }

    @Override
    public void save(String clientId, String contactInfo) {
        String sql = "INSERT INTO clients (customer_id, contact_info) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clientId);
            stmt.setString(2, contactInfo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении клиента", e);
        }
    }

    @Override
    public Client findById(String customerId) {
        String sql = "SELECT * FROM clients WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Client(
                        customerId,
                        rs.getString("name"),
                        rs.getString("contact_info")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске клиента", e);
        }
    }

    @Override
    public void update(Client client) {
        String sql = "UPDATE clients SET name = ?, contact_info = ? WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getContactInfo());
            stmt.setString(3, client.getCustomerID());
            stmt.executeUpdate();
            System.out.println("Клиент " + client.getName() + " обновлен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении клиента", e);
        }
    }

    @Override
    public void delete(String customerId) {
        String sql = "DELETE FROM clients WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            stmt.executeUpdate();
            System.out.println("Клиент с ID " + customerId + " удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении клиента", e);
        }
    }
}