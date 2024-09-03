package koh.db.hub.repository;

import koh.db.hub.DataHub;
import koh.db.hub.vps_management.enums.UserMetadataType;
import koh.db.hub.vps_management.tables.records.UserMetadataRecord;
import org.jooq.Record;
import org.jooq.Table;

import java.util.List;
import java.util.Optional;

public class UserMetadataRepository extends AbstractRepository {
    public List<UserMetadataRecord> getMetadata(Long userId) {
        return DataHub.useContext().fetch(USER_METADATA.where(USER_METADATA.USER_ID.eq(userId)));
    }

    public Optional<UserMetadataRecord> getMetadata(Long userId, UserMetadataType type) {

        Table<UserMetadataRecord> table = USER_METADATA.where(USER_METADATA.USER_ID
                .eq(userId)
                .and(USER_METADATA.TYPE.eq(type)));
        return Optional.ofNullable(DataHub.useContext().fetchOne(table));
    }

    public Optional<UserMetadataRecord> getMetadata(String email, UserMetadataType type) {
        Table<Record> table = USER_METADATA
                .join(USER)
                .on(USER.ID.eq(USER_METADATA.USER_ID))
                .where(USER.EMAIL.eq(email).and(USER_METADATA.TYPE.eq(type)));
        return Optional.ofNullable(DataHub.useContext().fetchOne(table)).map(r -> r.into(UserMetadataRecord.class));
    }

    public Optional<UserMetadataRecord> createMetadata(
            Long userId, String name, UserMetadataType type, byte[] data, boolean hidden
    ) {
        UserMetadataRecord r = DataHub
                .useContext()
                .newRecord(USER_METADATA)
                .setUserId(userId)
                .setName(name)
                .setType(type)
                .setBlob(data)
                .setHidden(hidden);
        return r.insert() == 1 ? Optional.of(r) : Optional.empty();
    }

    public Optional<UserMetadataRecord> createSecretMetadata(Long userId, String name, byte[] data) {
        UserMetadataRecord r = DataHub
                .useContext()
                .newRecord(USER_METADATA)
                .setUserId(userId)
                .setName(name)
                .setType(UserMetadataType.ENVIRONMENT)
                .setBlob(data)
                .setHidden(true);
        return r.insert() == 1 ? Optional.of(r) : Optional.empty();
    }

    public Optional<UserMetadataRecord> updateMetadata(Long id, byte[] data) {
        UserMetadataRecord r = DataHub.useContext().fetchOne(USER_METADATA.where(USER_METADATA.ID.eq(id)));
        if (r != null) {
            r.setBlob(data);
            return r.insert() == 1 ? Optional.of(r) : Optional.empty();
        }
        return Optional.empty();
    }
}
