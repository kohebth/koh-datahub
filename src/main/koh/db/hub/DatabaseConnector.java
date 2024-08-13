package koh.db.hub;

import javax.sql.DataSource;

public interface DatabaseConnector {
    DataSource getDataSource();
}
