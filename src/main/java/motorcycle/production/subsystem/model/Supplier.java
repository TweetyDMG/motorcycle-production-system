package motorcycle.production.subsystem.model;

import java.util.UUID;

public class Supplier {
    private String supplierID;
    private String materialID;
    private String name;
    private String contactInfo;

    public Supplier(String materialID, String name, String contactInfo) {
        this.supplierID = UUID.randomUUID().toString();
        this.materialID = materialID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public Supplier(String supplierID, String materialID, String name, String contactInfo) {
        this.supplierID = supplierID;
        this.materialID = materialID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Геттеры и сеттеры
    public String getSupplierID() { return supplierID; }
    public String getMaterialID() { return materialID; }
    public String getName() { return name; }
    public String getContactInfo() { return contactInfo; }
    public void setName(String name) { this.name = name; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    // Методы
    public void supplyMaterial(Material material, int quantity) {
        System.out.println("Поставщик " + name + " поставляет материал: " + material.getName() + ", количество: " + quantity);
        material.updateQuantity(quantity);
    }

    public String getSupplierDetails() {
        return "Supplier{ID='" + supplierID + "', materialID='" + materialID + "', name='" + name +
                "', contactInfo='" + contactInfo + "'}";
    }

    @Override
    public String toString() {
        return getSupplierDetails();
    }
}