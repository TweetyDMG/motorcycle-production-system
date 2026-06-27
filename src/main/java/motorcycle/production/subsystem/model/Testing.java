package motorcycle.production.subsystem.model;

import java.util.UUID;

public class Testing {
    private String testID;
    private String motorcycleID;
    private String result;
    private String issues;

    public Testing(String motorcycleID) {
        this.testID = UUID.randomUUID().toString();
        this.motorcycleID = motorcycleID;
        this.result = "Pending";
        this.issues = "";
    }

    public Testing(String testID, String motorcycleID, String result, String issues) {
        this.testID = testID;
        this.motorcycleID = motorcycleID;
        this.result = result;
        this.issues = issues;
    }

    // Геттеры и сеттеры
    public String getTestID() { return testID; }
    public String getMotorcycleID() { return motorcycleID; }
    public String getResult() { return result; }
    public String getIssues() { return issues; }
    public void setResult(String result) { this.result = result; }
    public void setIssues(String issues) { this.issues = issues; }

    // Методы
    public void performTest() {
        this.result = "Passed"; // Заглушка, реальная логика позже
        System.out.println("Тестирование " + testID + " для мотоцикла " + motorcycleID + " выполнено");
    }

    public void fixIssues() {
        this.issues = "Fixed";
        System.out.println("Дефекты для теста " + testID + " устранены");
    }

    @Override
    public String toString() {
        return "Testing{testID='" + testID + "', motorcycleID='" + motorcycleID + "', result='" + result +
                "', issues='" + issues + "'}";
    }
}