package koh.db.hub.repository;

import koh.db.hub.EntityManager;
import koh.db.hub.vps_management.tables.records.DockerImageRecord;

public class ImageRepository extends AbstractRepository {
    public DockerImageRecord getImageById(Long imageId) {
        return EntityManager.useContext().fetchOne(DOCKER_IMAGE.where(DOCKER_IMAGE.ID.eq(imageId)));
    }

    public DockerImageRecord createImage(String name, String repo, String version) {
        DockerImageRecord imageRecord = EntityManager
                .useContext()
                .newRecord(DOCKER_IMAGE)
                .setName(name)
                .setRepo(repo)
                .setVersion(version);
        return imageRecord.insert() == 1 ? imageRecord : null;
    }
}
