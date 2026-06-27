package motorcycle.production.subsystem.dao;

import motorcycle.production.subsystem.model.Testing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestingDAOImpl implements TestingDAO {
    @Override
    public void save(Testing testing) {
        String sql = "INSERT INTO tests (test_id, motorcycle_id, result, issues) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, testing.getTestID());
            stmt.setString(2, testing.getMotorcycleID());
            stmt.setString(3, testing.getResult());
            stmt.setString(4, testing.getIssues());
            stmt.executeUpdate();
            System.out.println("Тест " + testing.getTestID() + " сохранен в базе данных");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении теста", e);
        }
    }

    @Override
    public Testing findById(String testId) {
        String sql = "SELECT * FROM tests WHERE test_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, testId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Testing(
                        testId,
                        rs.getString("motorcycle_id"),
                        rs.getString("result"),
                        rs.getString("issues")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске теста", e);
        }
    }

    @Override
    public void update(Testing testing) {
        String sql = "UPDATE tests SET motorcycle_id = ?, result = ?, issues = ? WHERE test_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, testing.getMotorcycleID());
            stmt.setString(2, testing.getResult());
            stmt.setString(3, testing.getIssues());
            stmt.setString(4, testing.getTestID());
            stmt.executeUpdate();
            System.out.println("Тест " + testing.getTestID() + " обновлен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении теста", e);
        }
    }

    @Override
    public void delete(String testId) {
        String sql = "DELETE FROM tests WHERE test_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, testId);
            stmt.executeUpdate();
            System.out.println("Тест с ID " + testId + " удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении теста", e);
        }
    }
}
