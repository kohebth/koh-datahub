package koh.db.hub.repository;

import koh.db.hub.DataHub;
import koh.db.hub.vps_management.tables.records.DockerContainerRecord;

public class ContainerRepository extends AbstractRepository {
    public DockerContainerRecord createContainer(Long userId, String name, Integer memoryInMb) {
        DockerContainerRecord containerRecord = DataHub
                .useContext()
                .newRecord(DOCKER_CONTAINER)
                .setName(name)
                .setMemory(1024L * 1024 * memoryInMb);
        return containerRecord.insert() == 1 ? containerRecord : null;
    }

    public DockerContainerRecord getContainerById(Long containerId) {
        return DataHub.useContext().fetchOne(DOCKER_CONTAINER.where(DOCKER_CONTAINER.ID.eq(containerId)));
    }

    public DockerContainerRecord getContainerByName(String containerName) {
        return DataHub.useContext().fetchOne(DOCKER_CONTAINER.where(DOCKER_CONTAINER.NAME.eq(containerName)));
    }
}
