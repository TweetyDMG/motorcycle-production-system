package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Testing;

public interface TestingDAO {
    void save(Testing testing);
    Testing findById(String testId);
    void update(Testing testing);
    void delete(String testId);
}