package motorcycle.production.subsystem.service;

import motorcycle.production.subsystem.dao.WorkerDAO;
import motorcycle.production.subsystem.model.Worker;

import java.util.UUID;

public class WorkerService {
    private final WorkerDAO workerDAO;

    public WorkerService(WorkerDAO workerDAO) {
        this.workerDAO = workerDAO;
    }

    public void createWorker(String name, String specialization) {
        if (name == null || name.trim().isEmpty() || specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя и специализация не могут быть пустыми");
        }
        Worker worker = new Worker(name, specialization);
        workerDAO.save(worker);
        System.out.println("Работник " + name + " создан");
    }

    public Worker getWorkerById(String workerId) {
        Worker worker = workerDAO.findById(workerId);
        if (worker == null) {
            throw new IllegalArgumentException("Работник с ID " + workerId + " не найден");
        }
        return worker;
    }

    public void updateWorker(String workerId, String name, String specialization) {
        Worker worker = getWorkerById(workerId);
        worker.setName(name);
        worker.setSpecialization(specialization);
        workerDAO.update(worker);
        System.out.println("Работник " + name + " обновлен");
    }

    public void deleteWorker(String workerId) {
        Worker worker = getWorkerById(workerId);
        workerDAO.delete(workerId);
        System.out.println("Работник " + worker.getName() + " удален");
    }

    public void registerWorker(String userId, String subrole) {
        String workerId = userId; // Связь через совпадение ID
        workerDAO.save(workerId, subrole);
    }
}