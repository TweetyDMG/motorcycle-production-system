package motorcycle.production.subsystem.model;

import java.util.UUID;

public class Material {
    private String materialID;
    private String name;
    private int quantity;

    public Material(String name, int quantity) {
        this.materialID = UUID.randomUUID().toString();
        this.name = name;
        this.quantity = quantity;
    }

    public Material(String materialID, String name, int quantity) {
        this.materialID = materialID;
        this.name = name;
        this.quantity = quantity;
    }

    // Геттеры и сеттеры
    public String getMaterialID() { return materialID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // Методы
    public boolean checkAvailability(int requiredQuantity) {
        boolean available = quantity >= requiredQuantity;
        System.out.println("Проверка наличия материала " + name + ": " + (available ? "Доступно" : "Недостаточно"));
        return available;
    }

    public void updateQuantity(int change) {
        this.quantity += change;
        System.out.println("Количество материала " + name + " обновлено: " + quantity);
    }

    @Override
    public String toString() {
        return "Material{materialID='" + materialID + "', name='" + name + "', quantity=" + quantity + "}";
    }
}