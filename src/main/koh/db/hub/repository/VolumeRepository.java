package koh.db.hub.repository;

import koh.db.hub.DataHub;
import koh.db.hub.vps_management.enums.DockerVolumeType;
import koh.db.hub.vps_management.tables.records.DockerVolumeRecord;

import java.util.List;

public class VolumeRepository extends AbstractRepository {
    public DockerVolumeRecord getVolumeById(Long id) {
        return DataHub.useContext().fetchOne(DOCKER_VOLUME.where(DOCKER_VOLUME.ID.eq(id)));
    }

    public List<DockerVolumeRecord> getVolumeByIds(List<Long> ids) {
        return DataHub.useContext().fetch(DOCKER_VOLUME.where(DOCKER_VOLUME.ID.in(ids)));
    }

    public DockerVolumeRecord createVolume(String name, String host, String virtual) {
        DockerVolumeRecord r = DataHub
                .useContext()
                .newRecord(DOCKER_VOLUME)
                .setName(name)
                .setHost(host)
                .setVirtual(virtual)
                .setType(DockerVolumeType.BIND);
        return r.insert() == 1 ? r : null;
    }
}
