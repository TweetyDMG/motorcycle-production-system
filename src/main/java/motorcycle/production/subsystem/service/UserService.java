package motorcycle.production.subsystem.service;

import motorcycle.production.subsystem.dao.UserDAO;
import motorcycle.production.subsystem.model.User;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;
    private final ClientService clientService;
    private final ManagerService managerService;
    private final WorkerService workerService;

    public UserService(UserDAO userDAO, ClientService clientService, ManagerService managerService, WorkerService workerService) {
        this.userDAO = userDAO;
        this.clientService = clientService;
        this.managerService = managerService;
        this.workerService = workerService;
    }

    public User loginUser(String login, String password) {
        User user = userDAO.findByLogin(login);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Неверный логин или пароль");
        }
        return user;
    }

    public void registerUser(String login, String password, String role, String subrole) {
        if (userDAO.findByLogin(login) != null) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует");
        }
        String userId = UUID.randomUUID().toString();
        User user = new User(login, password, role, subrole);
        userDAO.save(user);

        switch (role) {
            case "Клиент":
                clientService.registerClient(userId, "default_contact");
                break;
            case "Менеджер":
                managerService.registerManager(userId);
                break;
            case "Работник":
                if (subrole == null) {
                    throw new IllegalArgumentException("Для роли Работник необходимо указать подроль");
                }
                workerService.registerWorker(userId, subrole);
                break;
            case "Логист":
                break;
            default:
                throw new IllegalArgumentException("Неверная роль");
        }
    }
}