package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Client;

public interface ClientDAO {
    void save(Client client);
    void save(String customerID, String contactInfo);
    Client findById(String customerId);
    void update(Client client);
    void delete(String customerId);
}