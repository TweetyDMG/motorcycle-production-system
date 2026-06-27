package motorcycle.production.subsystem.model;

import java.util.UUID;

public class Motorcycle {
    private String motorcycleID;
    private String model;
    private Component[] components;
    private String status;

    public Motorcycle(String model, Component[] components) {
        this.motorcycleID = UUID.randomUUID().toString();
        this.model = model;
        this.components = components;
        this.status = "Not Started";
    }

    public Motorcycle(String motorcycleID, String model, Component[] components, String status) {
        this.motorcycleID = motorcycleID;
        this.model = model;
        this.components = components;
        this.status = status;
    }

    // Геттеры и сеттеры
    public String getMotorcycleID() { return motorcycleID; }
    public String getModel() { return model; }
    public Component[] getComponents() { return components; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Методы
    public void assemble() {
        this.status = "Assembled";
        System.out.println("Мотоцикл " + model + " собран");
    }

    public void test() {
        this.status = "Tested";
        System.out.println("Мотоцикл " + model + " протестирован");
    }

    public void deliver() {
        this.status = "Delivered";
        System.out.println("Мотоцикл " + model + " доставлен");
    }

    @Override
    public String toString() {
        return "Motorcycle{motorcycleID='" + motorcycleID + "', model='" + model + "', status='" + status + "'}";
    }
}