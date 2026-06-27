package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Delivery;

import java.util.List;


public interface DeliveryDAO {
    void save(Delivery delivery);
    Delivery findById(String deliveryId);
    void update(Delivery delivery);
    void delete(String deliveryId);
    List<Delivery> findAll();
}