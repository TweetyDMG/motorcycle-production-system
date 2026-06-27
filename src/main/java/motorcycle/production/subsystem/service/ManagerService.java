package motorcycle.production.subsystem.service;

import motorcycle.production.subsystem.dao.ManagerDAO;
import motorcycle.production.subsystem.model.Manager;
import motorcycle.production.subsystem.model.Order;
import motorcycle.production.subsystem.model.Supplier;

import java.util.UUID;

public class ManagerService {
    private final ManagerDAO managerDAO;
    private final OrderService orderService;
    private final SupplierService supplierService;

    public ManagerService(ManagerDAO managerDAO, OrderService orderService, SupplierService supplierService) {
        this.managerDAO = managerDAO;
        this.orderService = orderService;
        this.supplierService = supplierService;
    }

    public void createManager(String name, String contactInfo) {
        if (name == null || name.trim().isEmpty() || contactInfo == null || contactInfo.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя и контактная информация не могут быть пустыми");
        }
        Manager manager = new Manager(name, contactInfo);
        managerDAO.save(manager);
        System.out.println("Менеджер " + name + " создан");
    }

    public Manager getManagerById(String managerId) {
        Manager manager = managerDAO.findById(managerId);
        if (manager == null) {
            throw new IllegalArgumentException("Менеджер с ID " + managerId + " не найден");
        }
        return manager;
    }

    public void manageOrder(String managerId, String orderId, String status) {
        Manager manager = getManagerById(managerId);
        Order order = orderService.getOrderById(orderId);
        manager.manageOrders(order);
        orderService.updateOrderStatus(orderId, status);
        System.out.println("Менеджер " + manager.getName() + " обновил заказ " + orderId);
    }

    public void manageSupplier(String managerId, String supplierId) {
        Manager manager = getManagerById(managerId);
        Supplier supplier = supplierService.getSupplierById(supplierId);
        manager.manageSuppliers(supplier);
        System.out.println("Менеджер " + manager.getName() + " управляет поставщиком " + supplier.getName());
    }

    public void deleteManager(String managerId) {
        Manager manager = getManagerById(managerId);
        managerDAO.delete(managerId);
        System.out.println("Менеджер " + managerId + " удален");
    }

    public void registerManager(String userId) {
        String managerId = userId; // Связь через совпадение ID
        managerDAO.save(managerId);
    }
}