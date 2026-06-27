package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Manager;

public interface ManagerDAO {
    void save(String managerId);
    Manager findById(String managerId);
    void update(Manager manager);
    void delete(String managerId);
    void save(Manager manager);
}