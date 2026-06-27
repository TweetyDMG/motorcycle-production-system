package motorcycle.production.subsystem.model;

import java.util.UUID;

public class Worker {
    private String workerID;
    private String name;
    private String specialization;

    public Worker(String name, String specialization) {
        this.workerID = UUID.randomUUID().toString();
        this.name = name;
        this.specialization = specialization;
    }

    public Worker(String workerID, String name, String specialization) {
        this.workerID = workerID;
        this.name = name;
        this.specialization = specialization;
    }

    // Геттеры и сеттеры
    public String getWorkerID() { return workerID; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public void setName(String name) { this.name = name; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    @Override
    public String toString() {
        return "Worker{workerID='" + workerID + "', name='" + name + "', specialization='" + specialization + "'}";
    }
}