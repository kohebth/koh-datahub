package koh.db.hub.repository;

import koh.db.hub.DataHub;
import koh.db.hub.vps_management.enums.DockerVolumeType;
import koh.db.hub.vps_management.enums.UserMetadataType;
import koh.db.hub.vps_management.tables.records.DockerVolumeRecord;
import koh.db.hub.vps_management.tables.records.UserMetadataRecord;
import org.jooq.Record;
import org.jooq.Table;

import java.util.List;

public class UserMetadataRepository extends AbstractRepository {
    List<UserMetadataRecord> getMetadataByUserId(Long userId) {
        return DataHub.useContext().fetch(USER_METADATA.where(USER_METADATA.USER_ID.eq(userId)));
    }

    List<UserMetadataRecord> getMetadataByUserEmail(String email) {
        Table<Record> table = USER_METADATA
                .join(USER)
                .on(USER.ID.eq(USER_METADATA.USER_ID))
                .where(USER.EMAIL.eq(email));
        return DataHub.useContext().fetch(table).into(UserMetadataRecord.class);
    }

    UserMetadataRecord createMetadata(Long userId, String name, UserMetadataType type, byte[] data, boolean hidden) {
        UserMetadataRecord r = DataHub
                .useContext()
                .newRecord(USER_METADATA)
                .setUserId(userId)
                .setName(name)
                .setType(type)
                .setBlob(data)
                .setHidden(hidden);
        return r.insert() == 1 ? r : null;
    }

    UserMetadataRecord createSecretMetadata(Long userId, String name, byte[] data) {
        UserMetadataRecord r = DataHub
                .useContext()
                .newRecord(USER_METADATA)
                .setUserId(userId)
                .setName(name)
                .setType(UserMetadataType.ENVIRONMENT)
                .setBlob(data)
                .setHidden(true);
        return r.insert() == 1 ? r : null;
    }

    UserMetadataRecord updateMetadata(Long id, byte[] data) {
        UserMetadataRecord r = DataHub
                .useContext()
                .fetchOne(USER_METADATA.where(USER_METADATA.ID.eq(id)));
        if (r != null) {
            r.setBlob(data);
            return r.insert() == 1 ? r : null;
        }
        return null;
    }
}
