package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Worker;

public interface WorkerDAO {
    void save(Worker worker);
    void save(String workerId, String subrole);
    Worker findById(String workerId);
    void update(Worker worker);
    void delete(String workerId);
}