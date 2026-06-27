package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Supplier;

public interface SupplierDAO {
    void save(Supplier supplier);
    Supplier findById(String supplierId);
    void update(Supplier supplier);
    void delete(String supplierId);
}