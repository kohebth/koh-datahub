package koh.db.hub.repository;

import koh.db.hub.EntityManager;
import koh.db.hub.vps_management.tables.records.ContainerImageRecord;
import koh.db.hub.vps_management.tables.records.DockerContainerRecord;
import koh.db.hub.vps_management.tables.records.DockerImageRecord;

public class ContainerImageRepository extends AbstractRepository {
    public ContainerImageRecord createContainerImageConnect(
            DockerContainerRecord container,
            DockerImageRecord image
    ) {
        ContainerImageRecord containerImage = EntityManager
                .useContext()
                .newRecord(CONTAINER_IMAGE)
                .setContainerId(container.getId())
                .setImageId(image.getId());
        return containerImage.insert() == 1 ? containerImage : null;
    }

    public void deleteContainerImageConnect(DockerContainerRecord container, DockerImageRecord image) {
        EntityManager
                .useContext()
                .newRecord(CONTAINER_IMAGE)
                .setContainerId(container.getId())
                .setImageId(image.getId())
                .delete();
    }
}
