package motorcycle.production.subsystem.service;

import motorcycle.production.subsystem.dao.DeliveryDAO;
import motorcycle.production.subsystem.dao.MotorcycleDAO;
import motorcycle.production.subsystem.model.Delivery;
import motorcycle.production.subsystem.model.Motorcycle;

import java.util.List;

public class DeliveryService {
    private final DeliveryDAO deliveryDAO;
    private final MotorcycleService motorcycleService;
    private final ClientService clientService;
    private final MotorcycleDAO motorcycleDAO;

    public DeliveryService(DeliveryDAO deliveryDAO, MotorcycleService motorcycleService, ClientService clientService, MotorcycleDAO motorcycleDAO) {
        this.deliveryDAO = deliveryDAO;
        this.motorcycleService = motorcycleService;
        this.clientService = clientService;
        this.motorcycleDAO = motorcycleDAO;
    }

    public void createDelivery(String motorcycleId, String customerId, String destination) {
        motorcycleService.getMotorcycleById(motorcycleId);
        clientService.getClientById(customerId);
        Delivery delivery = new Delivery(motorcycleId, customerId, destination);
        deliveryDAO.save(delivery);
    }

    public Delivery getDeliveryById(String deliveryId) {
        Delivery delivery = deliveryDAO.findById(deliveryId);
        if (delivery == null) {
            throw new IllegalArgumentException("Доставка с ID " + deliveryId + " не найдена");
        }
        return delivery;
    }

    public void deliverMotorcycle(String deliveryId) {
        Delivery delivery = getDeliveryById(deliveryId);
        Motorcycle motorcycle = motorcycleService.getMotorcycleById(delivery.getMotorcycleID());
        motorcycle.deliver();
        delivery.deliverMotorcycle();
        deliveryDAO.update(delivery);
        motorcycleDAO.update(motorcycle);
    }

    public void deleteDelivery(String deliveryId) {
        Delivery delivery = getDeliveryById(deliveryId);
        deliveryDAO.delete(deliveryId);
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryDAO.findAll();
    }
}