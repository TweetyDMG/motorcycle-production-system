package motorcycle.production.subsystem.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database connection provider for PostgreSQL.
 * <p>
 * Connection parameters are read from environment variables first,
 * then from a {@code .env} file in the project root directory.
 * If neither source provides a value, hardcoded defaults are used
 * (suitable for local development).
 * </p>
 * <pre>
 * Environment variables (or .env keys):
 *   DB_HOST     – default: localhost
 *   DB_PORT     – default: 5433
 *   DB_NAME     – default: motorcycle_production
 *   DB_USER     – default: postgres
 *   DB_PASSWORD – default: 1234
 * </pre>
 */
public final class DatabaseConnection {

    private static final Logger LOG = Logger.getLogger(DatabaseConnection.class.getName());

    private static final String JDBC_URL;
    private static final String DB_USER;
    private static final String DB_PASSWORD;

    static {
        Map<String, String> env = loadEnvFile(".env");
        String host     = firstOf(System.getenv("DB_HOST"),     env.get("DB_HOST"),     "localhost");
        String port     = firstOf(System.getenv("DB_PORT"),     env.get("DB_PORT"),     "5433");
        String dbName   = firstOf(System.getenv("DB_NAME"),     env.get("DB_NAME"),     "motorcycle_production");
        DB_USER         = firstOf(System.getenv("DB_USER"),     env.get("DB_USER"),     "postgres");
        DB_PASSWORD     = firstOf(System.getenv("DB_PASSWORD"), env.get("DB_PASSWORD"), "1234");
        JDBC_URL = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        LOG.info("Database URL: " + JDBC_URL.replace(DB_PASSWORD, "****"));
    }

    private DatabaseConnection() {
        // utility class
    }

    /**
     * Returns a new JDBC connection to the configured database.
     * <p>
     * Callers <b>must</b> close the connection (ideally via try-with-resources).
     * </p>
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    }

    // ---------------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------------

    /** Returns the first non-{@code null} value. */
    private static String firstOf(String... candidates) {
        for (String c : candidates) {
            if (c != null && !c.isBlank()) return c;
        }
        return null;
    }

    /**
     * Minimal {@code .env} file parser.
     * Reads lines in {@code KEY=VALUE} format, skips comments ({@code #})
     * and blank lines. Trims surrounding whitespace. Does <b>not</b>
     * expand variable references.
     */
    static Map<String, String> loadEnvFile(String path) {
        Map<String, String> map = new HashMap<>();
        try {
            Path p = Paths.get(path);
            if (!Files.isRegularFile(p)) return map;
            for (String line : Files.readAllLines(p)) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                int sep = line.indexOf('=');
                if (sep < 1) continue;
                String key = line.substring(0, sep).trim();
                String val = line.substring(sep + 1).trim();
                if (!key.isEmpty()) map.put(key, val);
            }
        } catch (IOException e) {
            LOG.log(Level.FINE, "Could not read {0}: {1}", new Object[]{path, e.getMessage()});
        }
        return map;
    }
}
