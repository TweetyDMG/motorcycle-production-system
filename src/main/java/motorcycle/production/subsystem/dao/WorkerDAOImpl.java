package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerDAOImpl implements WorkerDAO {
    @Override
    public void save(Worker worker) {
        String sql = "INSERT INTO workers (worker_id, name, specialization) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, worker.getWorkerID());
            stmt.setString(2, worker.getName());
            stmt.setString(3, worker.getSpecialization());
            stmt.executeUpdate();
            System.out.println("Работник " + worker.getName() + " сохранен в базе данных");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении работника", e);
        }
    }

    @Override
    public void save(String workerId, String subrole) {
        String sql = "INSERT INTO workers (worker_id, subrole) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, workerId);
            stmt.setString(2, subrole);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении работника", e);
        }
    }

    @Override
    public Worker findById(String workerId) {
        String sql = "SELECT * FROM workers WHERE worker_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, workerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Worker(
                        workerId,
                        rs.getString("name"),
                        rs.getString("specialization")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске работника", e);
        }
    }

    @Override
    public void update(Worker worker) {
        String sql = "UPDATE workers SET name = ?, specialization = ? WHERE worker_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, worker.getName());
            stmt.setString(2, worker.getSpecialization());
            stmt.setString(3, worker.getWorkerID());
            stmt.executeUpdate();
            System.out.println("Работник " + worker.getName() + " обновлен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении работника", e);
        }
    }

    @Override
    public void delete(String workerId) {
        String sql = "DELETE FROM workers WHERE worker_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, workerId);
            stmt.executeUpdate();
            System.out.println("Работник с ID " + workerId + " удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении работника", e);
        }
    }
}