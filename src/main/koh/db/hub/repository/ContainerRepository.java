package koh.db.hub.repository;

import koh.db.hub.DataHub;
import koh.db.hub.vps_management.tables.records.DockerContainerRecord;

public class ContainerRepository extends AbstractRepository {
    public DockerContainerRecord createContainer(Long userId, String name) {
        DockerContainerRecord containerRecord = DataHub
                .useContext()
                .newRecord(DOCKER_CONTAINER)
                .setName(name);
        return containerRecord.insert() == 1 ? containerRecord : null;
    }

    public DockerContainerRecord getContainerById(Long containerId) {
        return DataHub.useContext().fetchOne(DOCKER_CONTAINER.where(DOCKER_CONTAINER.ID.eq(containerId)));
    }
}
