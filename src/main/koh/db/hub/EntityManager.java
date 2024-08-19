package koh.db.hub;

import lombok.NonNull;
import org.jooq.*;
import org.jooq.conf.SettingsTools;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import java.util.Collection;
import java.util.List;

public class EntityManager {
    private static DatabaseConnector connector;

    @NonNull
    public static DSLContext useContext() {
        return DSL.using(connector.getDataSource(), SQLDialect.MARIADB, SettingsTools.defaultSettings());
    }

    private EntityManager() {
    }

    public static void connect(String host, String port, String username, String password, String database) {
        connector = new MariaDBConnector(host, port, username, password, database);
    }
}
