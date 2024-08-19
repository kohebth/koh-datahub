package koh.db.hub;

import lombok.extern.slf4j.Slf4j;
import org.mariadb.jdbc.Configuration;
import org.mariadb.jdbc.HostAddress;
import org.mariadb.jdbc.MariaDbPoolDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
class MariaDBConnector {
    private final MariaDbPoolDataSource dataSource;

    public MariaDBConnector(String host, String port, String user, String password, String database) {
        try {
            log.info("Detected driver: {}", org.mariadb.jdbc.Driver.class.getName());

            Configuration configuration = new Configuration.Builder()
                    .addresses(HostAddress.from(host, Integer.parseInt(port), true))
                    .user(user)
                    .database(database)
                    .autocommit(true)
                    .connectTimeout(30000)
                    .maxIdleTime(300000)
                    .pool(true)
                    .maxPoolSize(16)
                    .minPoolSize(4)
                    .poolValidMinDelay(1)
                    .allowMultiQueries(true)
                    .build();

            dataSource = new MariaDbPoolDataSource(configuration.initialUrl() + "&password=" + password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to configure DataSource");
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
