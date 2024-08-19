package koh.db.hub.repository;

import koh.db.hub.DataHub;
import koh.db.hub.vps_management.tables.records.ContainerNetworkRecord;
import koh.db.hub.vps_management.tables.records.DockerContainerRecord;
import koh.db.hub.vps_management.tables.records.DockerNetworkRecord;

public class ContainerNetworkRepository extends AbstractRepository {
    public ContainerNetworkRecord createContainerNetworkConnect(
            DockerContainerRecord container,
            DockerNetworkRecord network
    ) {
        ContainerNetworkRecord containerNetwork = DataHub
                .useContext()
                .newRecord(CONTAINER_NETWORK)
                .setContainerId(container.getId())
                .setNetworkId(network.getId());
        return containerNetwork.insert() == 1 ? containerNetwork : null;
    }

    public void deleteContainerNetworkConnect(DockerContainerRecord container, DockerNetworkRecord image) {
        DataHub
                .useContext()
                .newRecord(CONTAINER_NETWORK)
                .setContainerId(container.getId())
                .setNetworkId(image.getId())
                .delete();
    }
}
