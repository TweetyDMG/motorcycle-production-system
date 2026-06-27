package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Material;

public interface MaterialDAO {
    void save(Material material);
    Material findById(String materialId);
    void update(Material material);
    void delete(String materialId);
}