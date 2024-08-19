package koh.db.hub;

import lombok.NonNull;
import org.jooq.*;
import org.jooq.conf.SettingsTools;
import org.jooq.impl.DSL;

public class DataHub {
    private static MariaDBConnector connector;

    @NonNull
    public static DSLContext useContext() {
        return DSL.using(connector.getDataSource(), SQLDialect.MARIADB, SettingsTools.defaultSettings());
    }

    private DataHub() {
    }

    public static void connect(String host, String port, String username, String password, String database) {
        connector = new MariaDBConnector(host, port, username, password, database);
    }
}
