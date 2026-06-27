package motorcycle.production.subsystem.model;

import java.util.UUID;

public class Component {
    private String componentID;
    private String name;
    private String materialID;
    private String status;

    public Component(String name, String materialID) {
        this.componentID = UUID.randomUUID().toString();
        this.name = name;
        this.materialID = materialID;
        this.status = "Not Manufactured";
    }

    public Component(String name){
        this.componentID = UUID.randomUUID().toString();
        this.name = name;
        this.status = "Not Manufactured";
    }

    public Component(String componentID, String name, String materialID, String status) {
        this.componentID = componentID;
        this.name = name;
        this.materialID = materialID;
        this.status = status;
    }

    // Геттеры и сеттеры
    public String getComponentID() { return componentID; }
    public String getName() { return name; }
    public String getMaterialID() { return materialID; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Методы
    public void manufacture() {
        this.status = "Manufactured";
        System.out.println("Компонент " + name + " изготовлен");
    }

    public String getComponentDetails() {
        return "Component{ID='" + componentID + "', name='" + name + "', materialID='" + materialID +
                "', status='" + status + "'}";
    }

    @Override
    public String toString() {
        return getComponentDetails();
    }
}