package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public void save(Order order) {
        String sql = "INSERT INTO orders (order_id, customer_id, motorcycle_id, order_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, order.getOrderID());
            stmt.setString(2, order.getCustomerID());
            stmt.setString(3, order.getMotorcycleID());
            stmt.setString(4, order.getOrderDate());
            stmt.setString(5, order.getStatus());
            stmt.executeUpdate();
            System.out.println("Заказ " + order.getOrderID() + " сохранен в базе данных");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении заказа", e);
        }
    }

    @Override
    public Order findById(String orderId) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                            rs.getString("order_id"),
                            rs.getString("customer_id"),
                            rs.getString("motorcycle_id"),
                            rs.getString("order_date"),
                            rs.getString("status")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске заказа", e);
        }
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE orders SET customer_id = ?, motorcycle_id = ?, order_date = ?, status = ? WHERE order_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, order.getCustomerID());
            stmt.setString(2, order.getMotorcycleID());
            stmt.setString(3, order.getOrderDate());
            stmt.setString(4, order.getStatus());
            stmt.setString(5, order.getOrderID());
            stmt.executeUpdate();
            System.out.println("Заказ " + order.getOrderID() + " обновлен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении заказа", e);
        }
    }

    @Override
    public void delete(String orderId) {
        String sql = "DELETE FROM orders WHERE order_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, orderId);
            stmt.executeUpdate();
            System.out.println("Заказ с ID " + orderId + " удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении заказа", e);
        }
    }

    @Override
    public List<Order> findByClientId(String clientId) {
        String sql = "SELECT * FROM orders WHERE customer_id = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(new Order(
                            rs.getString("order_id"),
                            rs.getString("customer_id"),
                            rs.getString("motorcycle_id"),
                            rs.getString("order_date"),
                            rs.getString("status")
                    ));
                }
                return orders;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске заказов по ID клиента", e);
        }
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                orders.add(new Order(
                        rs.getString("order_id"),
                        rs.getString("customer_id"),
                        rs.getString("motorcycle_id"),
                        rs.getString("order_date"),
                        rs.getString("status")
                ));
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении всех заказов", e);
        }
    }
}