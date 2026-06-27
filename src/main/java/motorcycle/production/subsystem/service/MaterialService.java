package motorcycle.production.subsystem.service;

import motorcycle.production.subsystem.dao.MaterialDAO;
import motorcycle.production.subsystem.model.Material;

public class MaterialService {
    private final MaterialDAO materialDAO;

    public MaterialService(MaterialDAO materialDAO) {
        this.materialDAO = materialDAO;
    }

    public void createMaterial(String name, int quantity) {
        if (name == null || name.trim().isEmpty() || quantity < 0) {
            throw new IllegalArgumentException("Имя и количество не могут быть некорректными");
        }
        Material material = new Material(name, quantity);
        materialDAO.save(material);
        System.out.println("Материал " + name + " создан");
    }

    public Material getMaterialById(String materialId) {
        Material material = materialDAO.findById(materialId);
        if (material == null) {
            throw new IllegalArgumentException("Материал с ID " + materialId + " не найден");
        }
        return material;
    }

    public void updateMaterial(Material material) {
        materialDAO.update(material);
        System.out.println("Материал " + material.getName() + " обновлен");
    }

    public void deleteMaterial(String materialId) {
        Material material = getMaterialById(materialId);
        materialDAO.delete(materialId);
        System.out.println("Материал " + materialId + " удален");
    }
}