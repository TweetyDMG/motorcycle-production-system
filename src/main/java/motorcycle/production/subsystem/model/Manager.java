package motorcycle.production.subsystem.model;

import java.util.UUID;

public class Manager {
    private String managerID;
    private String name;
    private String contactInfo;

    public Manager(String name, String contactInfo) {
        this.managerID = UUID.randomUUID().toString();
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public Manager(String managerID, String name, String contactInfo) {
        this.managerID = managerID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Геттеры и сеттеры
    public String getManagerID() { return managerID; }
    public String getName() { return name; }
    public String getContactInfo() { return contactInfo; }
    public void setName(String name) { this.name = name; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    // Методы
    public void manageOrders(Order order) {
        System.out.println("Менеджер " + name + " управляет заказом: " + order.getOrderID());
    }

    public void manageSuppliers(Supplier supplier) {
        System.out.println("Менеджер " + name + " управляет поставщиком: " + supplier.getName());
    }

    @Override
    public String toString() {
        return "Manager{managerID='" + managerID + "', name='" + name + "', contactInfo='" + contactInfo + "'}";
    }
}