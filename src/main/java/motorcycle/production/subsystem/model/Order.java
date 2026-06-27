package motorcycle.production.subsystem.model;

import java.time.LocalDate;
import java.util.UUID;

public class Order {
    private String orderID;
    private String customerID;
    private String motorcycleID;
    private String orderDate;
    private String status;

    public Order(String customerID, String motorcycleID) {
        this.orderID = UUID.randomUUID().toString();
        this.customerID = customerID;
        this.motorcycleID = motorcycleID;
        this.orderDate = LocalDate.now().toString();
        this.status = "Pending";
    }

    public Order(String customerID, String motorcycleID, String orderDate) {
        this.orderID = UUID.randomUUID().toString();
        this.customerID = customerID;
        this.motorcycleID = motorcycleID;
        this.orderDate = orderDate;
        this.status = "Pending";
    }

    public Order(String orderID, String customerID, String motorcycleID, String orderDate, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.motorcycleID = motorcycleID;
        this.orderDate = orderDate;
        this.status = status;
    }

    // Геттеры и сеттеры
    public String getOrderID() { return orderID; }
    public String getCustomerID() { return customerID; }
    public String getMotorcycleID() { return motorcycleID; }
    public String getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getOrderDetails() {
        return "Order{ID='" + orderID + "', customerID='" + customerID + "', motorcycleID='" + motorcycleID +
                "', date='" + orderDate + "', status='" + status + "'}";
    }

    @Override
    public String toString() {
        return getOrderDetails();
    }
}