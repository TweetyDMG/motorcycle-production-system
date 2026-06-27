package motorcycle.production.subsystem.service;

import motorcycle.production.subsystem.dao.OrderDAO;
import motorcycle.production.subsystem.model.Component;
import motorcycle.production.subsystem.model.Motorcycle;
import motorcycle.production.subsystem.model.Order;

import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO;
    private final ClientService clientService;
    private final MotorcycleService motorcycleService;

    public OrderService(OrderDAO orderDAO, ClientService clientService, MotorcycleService motorcycleService) {
        this.orderDAO = orderDAO;
        this.clientService = clientService;
        this.motorcycleService = motorcycleService;
    }

    public void createOrder(String customerId, String motorcycleModel, String orderDate) {
        clientService.getClientById(customerId);
        Component[] components = new Component[]{new Component("Engine"), new Component("Frame")};
        Motorcycle motorcycle = new Motorcycle(motorcycleModel, components);
        motorcycleService.saveMotorcycle(motorcycle);
        if (orderDate == null || orderDate.isEmpty()) {
            throw new IllegalArgumentException("Дата заказа не может быть пустой");
        }
        Order order = new Order(customerId, motorcycle.getMotorcycleID(), orderDate);
        orderDAO.save(order);
    }

    public Order getOrderById(String orderId) {
        Order order = orderDAO.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Заказ с ID " + orderId + " не найден");
        }
        return order;
    }

    public void updateOrderStatus(String orderId, String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Статус не может быть пустым");
        }
        Order order = getOrderById(orderId);
        order.setStatus(status);
        orderDAO.update(order);
    }

    public void deleteOrder(String orderId) {
        Order order = getOrderById(orderId);
        orderDAO.delete(orderId);
    }

    public List<Order> getOrdersByClient(String customerId) {
        return orderDAO.findByClientId(customerId);
    }

    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }
}