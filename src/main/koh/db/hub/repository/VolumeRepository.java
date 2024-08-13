package koh.db.hub.repository;

import koh.db.hub.EntityManager;
import koh.db.hub.vps_management.enums.DockerVolumeType;
import koh.db.hub.vps_management.tables.records.DockerVolumeRecord;

import java.util.List;

public class VolumeRepository extends AbstractRepository {
    public DockerVolumeRecord getVolumeById(Long id) {
        return EntityManager.useContext().fetchOne(DOCKER_VOLUME.where(DOCKER_VOLUME.ID.eq(id)));
    }

    public List<DockerVolumeRecord> getVolumeByIds(List<Long> ids) {
        return EntityManager.useContext().fetch(DOCKER_VOLUME.where(DOCKER_VOLUME.ID.in(ids)));
    }

    public DockerVolumeRecord createVolume(String name, String host, String virtual) {
        DockerVolumeRecord r = EntityManager
                .useContext()
                .newRecord(DOCKER_VOLUME)
                .setName(name)
                .setHost(host)
                .setVirtual(virtual)
                .setType(DockerVolumeType.BIND);
        return r.insert() == 1 ? r : null;
    }
}
