package koh.db.hub.repository;

import koh.db.hub.DataHub;
import koh.db.hub.vps_management.tables.records.ContainerVolumeRecord;
import koh.db.hub.vps_management.tables.records.DockerContainerRecord;
import koh.db.hub.vps_management.tables.records.DockerVolumeRecord;

public class ContainerVolumeRepository extends AbstractRepository {
    public ContainerVolumeRecord createContainerVolumeConnect(
            DockerContainerRecord container,
            DockerVolumeRecord volume
    ) {
        ContainerVolumeRecord containerVolume = DataHub
                .useContext()
                .newRecord(CONTAINER_VOLUME)
                .setContainerId(container.getId())
                .setVolumeId(volume.getId());
        return containerVolume.insert() == 1 ? containerVolume : null;
    }

    public void deleteContainerVolumeConnect(DockerContainerRecord container, DockerVolumeRecord volume) {
        DataHub
                .useContext()
                .newRecord(CONTAINER_VOLUME)
                .setContainerId(container.getId())
                .setVolumeId(volume.getId())
                .delete();
    }
}
