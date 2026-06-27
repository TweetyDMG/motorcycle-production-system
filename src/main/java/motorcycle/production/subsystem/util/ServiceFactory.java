package motorcycle.production.subsystem.util;

import motorcycle.production.subsystem.dao.*;
import motorcycle.production.subsystem.service.*;

/**
 * Central service-locator / factory for the application layer.
 * <p>
 * Every UI component should obtain its services through this class instead
 * of wiring DAOs and services manually. This decouples the UI layer from
 * concrete implementations and makes it possible to swap DAO implementations
 * in a single place.
 * </p>
 */
public final class ServiceFactory {

    // ---- DAOs (singletons) ------------------------------------------------
    private static final UserDAO USER_DAO = new UserDAOImpl();
    private static final ClientDAO CLIENT_DAO = new ClientDAOImpl();
    private static final OrderDAO ORDER_DAO = new OrderDAOImpl();
    private static final MotorcycleDAO MOTORCYCLE_DAO = new MotorcycleDAOImpl();
    private static final ComponentDAO COMPONENT_DAO = new ComponentDAOImpl();
    private static final MaterialDAO MATERIAL_DAO = new MaterialDAOImpl();
    private static final WorkerDAO WORKER_DAO = new WorkerDAOImpl();
    private static final ManagerDAO MANAGER_DAO = new ManagerDAOImpl();
    private static final SupplierDAO SUPPLIER_DAO = new SupplierDAOImpl();
    private static final DeliveryDAO DELIVERY_DAO = new DeliveryDAOImpl();
    private static final TestingDAO TESTING_DAO = new TestingDAOImpl();

    // ---- Services (lazy singletons) ---------------------------------------
    private static MaterialService materialService;
    private static ComponentService componentService;
    private static MotorcycleService motorcycleService;
    private static ClientService clientService;
    private static OrderService orderService;
    private static WorkerService workerService;
    private static ManagerService managerService;
    private static SupplierService supplierService;
    private static DeliveryService deliveryService;
    private static TestingService testingService;
    private static UserService userService;

    private ServiceFactory() {
        // utility class
    }

    // ---------------------------------------------------------------
    // Public accessors
    // ---------------------------------------------------------------

    public static synchronized MaterialService materialService() {
        if (materialService == null) materialService = new MaterialService(MATERIAL_DAO);
        return materialService;
    }

    public static synchronized ComponentService componentService() {
        if (componentService == null) componentService = new ComponentService(COMPONENT_DAO, materialService());
        return componentService;
    }

    public static synchronized MotorcycleService motorcycleService() {
        if (motorcycleService == null) motorcycleService = new MotorcycleService(MOTORCYCLE_DAO, componentService());
        return motorcycleService;
    }

    public static synchronized ClientService clientService() {
        if (clientService == null) clientService = new ClientService(CLIENT_DAO);
        return clientService;
    }

    public static synchronized OrderService orderService() {
        if (orderService == null) orderService = new OrderService(ORDER_DAO, clientService(), motorcycleService());
        return orderService;
    }

    public static synchronized WorkerService workerService() {
        if (workerService == null) workerService = new WorkerService(WORKER_DAO);
        return workerService;
    }

    public static synchronized ManagerService managerService() {
        if (managerService == null) managerService = new ManagerService(MANAGER_DAO, orderService(), supplierService());
        return managerService;
    }

    public static synchronized SupplierService supplierService() {
        if (supplierService == null) supplierService = new SupplierService(SUPPLIER_DAO, materialService());
        return supplierService;
    }

    public static synchronized DeliveryService deliveryService() {
        if (deliveryService == null) deliveryService = new DeliveryService(DELIVERY_DAO, motorcycleService(), clientService(), MOTORCYCLE_DAO);
        return deliveryService;
    }

    public static synchronized TestingService testingService() {
        if (testingService == null) testingService = new TestingService(TESTING_DAO, motorcycleService());
        return testingService;
    }

    public static synchronized UserService userService() {
        if (userService == null) userService = new UserService(USER_DAO, clientService(), managerService(), workerService());
        return userService;
    }
}
