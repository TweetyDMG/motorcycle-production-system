package motorcycle.production.subsystem.service;

import motorcycle.production.subsystem.dao.SupplierDAO;
import motorcycle.production.subsystem.model.Material;
import motorcycle.production.subsystem.model.Supplier;

public class SupplierService {
    private final SupplierDAO supplierDAO;
    private final MaterialService materialService;

    public SupplierService(SupplierDAO supplierDAO, MaterialService materialService) {
        this.supplierDAO = supplierDAO;
        this.materialService = materialService;
    }

    public void createSupplier(String materialId, String name, String contactInfo) {
        materialService.getMaterialById(materialId);
        if (name == null || name.trim().isEmpty() || contactInfo == null || contactInfo.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя и контактная информация не могут быть пустыми");
        }
        Supplier supplier = new Supplier(materialId, name, contactInfo);
        supplierDAO.save(supplier);
        System.out.println("Поставщик " + name + " создан");
    }

    public Supplier getSupplierById(String supplierId) {
        Supplier supplier = supplierDAO.findById(supplierId);
        if (supplier == null) {
            throw new IllegalArgumentException("Поставщик с ID " + supplierId + " не найден");
        }
        return supplier;
    }

    public void supplyMaterial(String supplierId, String materialId, int quantity) {
        Supplier supplier = getSupplierById(supplierId);
        Material material = materialService.getMaterialById(materialId);
        if (!supplier.getMaterialID().equals(materialId)) {
            throw new IllegalStateException("Поставщик " + supplier.getName() + " не поставляет материал " + material.getName());
        }
        supplier.supplyMaterial(material, quantity);
        materialService.updateMaterial(material);
        System.out.println("Поставщик " + supplier.getName() + " поставил " + quantity + " единиц материала " + material.getName());
    }

    public void deleteSupplier(String supplierId) {
        Supplier supplier = getSupplierById(supplierId);
        supplierDAO.delete(supplierId);
        System.out.println("Поставщик " + supplierId + " удален");
    }
}