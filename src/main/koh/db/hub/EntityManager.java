package koh.db.hub;

import lombok.NonNull;
import org.jooq.*;
import org.jooq.conf.SettingsTools;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import java.util.Collection;
import java.util.List;

public class EntityManager {
    private static final DatabaseConnector CONNECTOR = new MariaDBConnector();

    @NonNull
    public static DSLContext useContext() {
        return DSL.using(CONNECTOR.getDataSource(), SQLDialect.MARIADB, SettingsTools.defaultSettings());
    }

    public static <R extends Record, T extends TableImpl<R>> List<R> fetch(T table, Collection<Condition> conditions) {
        return useContext().selectFrom(table).where(conditions).fetchInto(table.getRecordType());
    }

    public static <R extends Record, T extends TableImpl<R>> R newRecord(T table) {
        return useContext().newRecord(table);
    }

    private EntityManager() {
    }
}
