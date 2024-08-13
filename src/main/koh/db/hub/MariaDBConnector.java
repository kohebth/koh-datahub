package koh.db.hub;

import lombok.extern.slf4j.Slf4j;
import org.mariadb.jdbc.Configuration;
import org.mariadb.jdbc.HostAddress;
import org.mariadb.jdbc.MariaDbPoolDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
class MariaDBConnector implements DatabaseConnector {
    static final String HOST;
    static final Integer PORT;
    static final String USER;
    static final String PASSWORD;
    static final String DATABASE;

    static {
        HOST = System.getenv("MARIADB_HOST");
        PORT = Integer.valueOf(System.getenv("MARIADB_PORT"));
        USER = System.getenv("MARIADB_USER");
        PASSWORD = System.getenv("MARIADB_PASSWORD");
        DATABASE = System.getenv("MARIADB_DATABASE");
    }

    private final MariaDbPoolDataSource dataSource;

    MariaDBConnector() {
        try {
            log.info("Detected driver: {}", org.mariadb.jdbc.Driver.class.getName());

            Configuration configuration = new Configuration.Builder()
                    .addresses(HostAddress.from(HOST, PORT, true))
                    .user(USER)
                    .database(DATABASE)
                    .autocommit(true)
                    .connectTimeout(30000)
                    .maxIdleTime(300000)
                    .pool(true)
                    .maxPoolSize(16)
                    .minPoolSize(4)
                    .poolValidMinDelay(1)
                    .allowMultiQueries(true)
                    .build();

            dataSource = new MariaDbPoolDataSource(configuration.initialUrl() + "&password=" + PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to configure DataSource");
        }
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
