package motorcycle.production.subsystem.service;

import motorcycle.production.subsystem.dao.TestingDAO;
import motorcycle.production.subsystem.model.Testing;

public class TestingService {
    private final TestingDAO testingDAO;
    private final MotorcycleService motorcycleService;

    public TestingService(TestingDAO testingDAO, MotorcycleService motorcycleService) {
        this.testingDAO = testingDAO;
        this.motorcycleService = motorcycleService;
    }

    public void createTest(String motorcycleId) {
        motorcycleService.getMotorcycleById(motorcycleId);
        Testing testing = new Testing(motorcycleId);
        testingDAO.save(testing);
        System.out.println("Тест " + testing.getTestID() + " создан");
    }

    public Testing getTestById(String testId) {
        Testing testing = testingDAO.findById(testId);
        if (testing == null) {
            throw new IllegalArgumentException("Тест с ID " + testId + " не найден");
        }
        return testing;
    }

    public void performTest(String testId, String result, String issues) {
        Testing testing = getTestById(testId);
        testing.setResult(result);
        testing.setIssues(issues);
        testing.performTest();
        testingDAO.update(testing);
        System.out.println("Тест " + testId + " выполнен с результатом: " + result);
    }

    public void fixIssues(String testId) {
        Testing testing = getTestById(testId);
        testing.fixIssues();
        testingDAO.update(testing);
        System.out.println("Дефекты теста " + testId + " устранены");
    }

    public void deleteTest(String testId) {
        Testing testing = getTestById(testId);
        testingDAO.delete(testId);
        System.out.println("Тест " + testId + " удален");
    }
}