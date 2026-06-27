package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Component;

public interface ComponentDAO {
    void save(Component component);
    Component findById(String componentId);
    void update(Component component);
    void delete(String componentId);
}