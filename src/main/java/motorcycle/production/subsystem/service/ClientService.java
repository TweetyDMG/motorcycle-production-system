package motorcycle.production.subsystem.service;

import motorcycle.production.subsystem.dao.ClientDAO;
import motorcycle.production.subsystem.model.Client;

import java.util.UUID;

public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void createClient(String name, String contactInfo) {
        if (name == null || name.trim().isEmpty() || contactInfo == null || contactInfo.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя и контактная информация не могут быть пустыми");
        }
        Client client = new Client(name, contactInfo);
        clientDAO.save(client);
        System.out.println("Клиент " + name + " создан");
    }

    public Client getClientById(String customerId) {
        Client client = clientDAO.findById(customerId);
        if (client == null) {
            throw new IllegalArgumentException("Клиент с ID " + customerId + " не найден");
        }
        return client;
    }

    public void updateClient(String customerId, String name, String contactInfo) {
        Client client = getClientById(customerId);
        client.setName(name);
        client.setContactInfo(contactInfo);
        clientDAO.update(client);
        System.out.println("Клиент " + name + " обновлен");
    }

    public void deleteClient(String customerId) {
        Client client = getClientById(customerId);
        clientDAO.delete(customerId);
        System.out.println("Клиент " + client.getName() + " удален");
    }

    public void registerClient(String userId, String contactInfo) {
        String clientId = userId;
        clientDAO.save(clientId, contactInfo);
    }
}