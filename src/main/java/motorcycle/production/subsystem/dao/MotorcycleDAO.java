package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Motorcycle;

public interface MotorcycleDAO {
    void save(Motorcycle motorcycle);
    Motorcycle findById(String motorcycleId);
    void update(Motorcycle motorcycle);
    void delete(String motorcycleId);
}