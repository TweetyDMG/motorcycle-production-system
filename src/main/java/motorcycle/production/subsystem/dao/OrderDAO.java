package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Order;

import java.util.List;

public interface OrderDAO {
    void save(Order order);
    Order findById(String orderId);
    void update(Order order);
    void delete(String orderId);
    List<Order> findByClientId(String clientId);
    List<Order> findAll();
}