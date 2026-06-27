package motorcycle.production.subsystem.service;

import motorcycle.production.subsystem.dao.MotorcycleDAO;
import motorcycle.production.subsystem.model.Component;
import motorcycle.production.subsystem.model.Motorcycle;

public class MotorcycleService {
    private final MotorcycleDAO motorcycleDAO;
    private final ComponentService componentService;

    public MotorcycleService(MotorcycleDAO motorcycleDAO, ComponentService componentService) {
        this.motorcycleDAO = motorcycleDAO;
        this.componentService = componentService;
    }

    public void saveMotorcycle(Motorcycle motorcycle) {
        motorcycleDAO.save(motorcycle);
    }

    public void createMotorcycle(String model, String[] componentIds) {
        Component[] components = new Component[componentIds.length];
        for (int i = 0; i < componentIds.length; i++) {
            components[i] = componentService.getComponentById(componentIds[i]);
        }
        Motorcycle motorcycle = new Motorcycle(model, components);
        motorcycleDAO.save(motorcycle);
        System.out.println("Мотоцикл " + model + " создан");
    }

    public Motorcycle getMotorcycleById(String motorcycleId) {
        Motorcycle motorcycle = motorcycleDAO.findById(motorcycleId);
        if (motorcycle == null) {
            throw new IllegalArgumentException("Мотоцикл с ID " + motorcycleId + " не найден");
        }
        return motorcycle;
    }

    public void assembleMotorcycle(String motorcycleId) {
        Motorcycle motorcycle = getMotorcycleById(motorcycleId);
        for (Component component : motorcycle.getComponents()) {
            if (!"Manufactured".equals(component.getStatus())) {
                throw new IllegalStateException("Компонент " + component.getName() + " не изготовлен");
            }
        }
        motorcycle.assemble();
        motorcycleDAO.update(motorcycle);
        System.out.println("Мотоцикл " + motorcycleId + " собран");
    }

    public void testMotorcycle(String motorcycleId) {
        Motorcycle motorcycle = getMotorcycleById(motorcycleId);
        motorcycle.test();
        motorcycleDAO.update(motorcycle);
        System.out.println("Мотоцикл " + motorcycleId + " протестирован");
    }

    public void deleteMotorcycle(String motorcycleId) {
        Motorcycle motorcycle = getMotorcycleById(motorcycleId);
        motorcycleDAO.delete(motorcycleId);
        System.out.println("Мотоцикл " + motorcycleId + " удален");
    }
}