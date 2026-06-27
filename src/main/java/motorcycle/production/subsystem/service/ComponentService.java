package motorcycle.production.subsystem.service;

import motorcycle.production.subsystem.dao.ComponentDAO;
import motorcycle.production.subsystem.model.Component;
import motorcycle.production.subsystem.model.Material;

public class ComponentService {
    private final ComponentDAO componentDAO;
    private final MaterialService materialService;

    public ComponentService(ComponentDAO componentDAO, MaterialService materialService) {
        this.componentDAO = componentDAO;
        this.materialService = materialService;
    }

    public void createComponent(String name, String materialId) {
        materialService.getMaterialById(materialId); // Проверка существования материала
        Component component = new Component(name, materialId);
        componentDAO.save(component);
        System.out.println("Компонент " + name + " создан");
    }

    public Component getComponentById(String componentId) {
        Component component = componentDAO.findById(componentId);
        if (component == null) {
            throw new IllegalArgumentException("Компонент с ID " + componentId + " не найден");
        }
        return component;
    }

    public void manufactureComponent(String componentId) {
        Component component = getComponentById(componentId);
        Material material = materialService.getMaterialById(component.getMaterialID());
        if (!material.checkAvailability(1)) { // Пример: требуется 1 единица материала
            throw new IllegalStateException("Недостаточно материала " + material.getName());
        }
        material.updateQuantity(-1); // Уменьшаем количество материала
        component.manufacture();
        componentDAO.update(component);
        materialService.updateMaterial(material);
        System.out.println("Компонент " + componentId + " изготовлен");
    }

    public void deleteComponent(String componentId) {
        Component component = getComponentById(componentId);
        componentDAO.delete(componentId);
        System.out.println("Компонент " + componentId + " удален");
    }

    public void updateComponent(String componentId, String name, String materialId) {
        Component component = getComponentById(componentId);
        componentDAO.update(component);
        System.out.println("Компонент " + name + " обновлен");
    }
}