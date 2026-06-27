package motorcycle.production.subsystem.model;

import java.util.UUID;

public class Delivery {
    private String deliveryID;
    private String motorcycleID;
    private String customerID;
    private String destination;
    private String status;

    public Delivery(String motorcycleID, String customerID, String destination) {
        this.deliveryID = UUID.randomUUID().toString();
        this.motorcycleID = motorcycleID;
        this.customerID = customerID;
        this.destination = destination;
        this.status = "Pending";
    }

    public Delivery(String deliveryID, String motorcycleID, String customerID, String destination, String status) {
        this.deliveryID = deliveryID;
        this.motorcycleID = motorcycleID;
        this.customerID = customerID;
        this.destination = destination;
        this.status = status;
    }

    // Геттеры и сеттеры
    public String getDeliveryID() { return deliveryID; }
    public String getMotorcycleID() { return motorcycleID; }
    public String getCustomerID() { return customerID; }
    public String getDestination() { return destination; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Методы
    public void deliverMotorcycle() {
        this.status = "Delivered";
        System.out.println("Доставка " + deliveryID + " выполнена в " + destination);
    }

    @Override
    public String toString() {
        return "Delivery{deliveryID='" + deliveryID + "', motorcycleID='" + motorcycleID + "', customerID='" +
                customerID + "', destination='" + destination + "', status='" + status + "'}";
    }
}