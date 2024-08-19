package koh.db.hub.repository;

import koh.db.hub.DataHub;
import koh.db.hub.vps_management.tables.records.UserContainerRecord;
import koh.db.hub.vps_management.tables.records.DockerContainerRecord;
import koh.db.hub.vps_management.tables.records.UserRecord;

public class UserContainerRepository extends AbstractRepository {
    public UserContainerRecord createUserContainerConnect(
            DockerContainerRecord container,
            UserRecord user
    ) {
        UserContainerRecord containerVolume = DataHub
                .useContext()
                .newRecord(USER_CONTAINER)
                .setContainerId(container.getId())
                .setUserId(user.getId());
        return containerVolume.insert() == 1 ? containerVolume : null;
    }

    public void deleteUserContainerConnect(DockerContainerRecord container, UserRecord user) {
        DataHub
                .useContext()
                .newRecord(USER_CONTAINER)
                .setContainerId(container.getId())
                .setUserId(user.getId())
                .delete();
    }
}
