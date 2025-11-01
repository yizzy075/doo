package co.edu.uco.HumanSolution.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Configuración de base de datos para JavaFX (Desktop)
 * Spring Boot usa su propia configuración desde application.properties
 */
public class DatabaseConfig {
    private static DatabaseConfig instance;
    private HikariDataSource dataSource;

    private DatabaseConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/sistema_usuarios");
        config.setUsername("postgres");
        config.setPassword("dino2020");
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(10);

        this.dataSource = new HikariDataSource(config);
    }

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closeConnection() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}