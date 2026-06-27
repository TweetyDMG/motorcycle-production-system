package motorcycle.production.subsystem.model;

import java.util.UUID;

public class Client {
    private String customerID;
    private String name;
    private String contactInfo;

    public Client(String name, String contactInfo) {
        this.customerID = UUID.randomUUID().toString();
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public Client(String customerID, String name, String contactInfo) {
        this.customerID = customerID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Геттеры и сеттеры
    public String getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getContactInfo() { return contactInfo; }
    public void setName(String name) { this.name = name; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    @Override
    public String toString() {
        return "Client{customerID='" + customerID + "', name='" + name + "', contactInfo='" + contactInfo + "'}";
    }
}