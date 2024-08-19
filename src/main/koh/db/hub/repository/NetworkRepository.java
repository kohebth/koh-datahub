package koh.db.hub.repository;

import koh.db.hub.DataHub;
import koh.db.hub.vps_management.tables.records.DockerNetworkRecord;

import java.util.List;

public class NetworkRepository extends AbstractRepository {
    public DockerNetworkRecord createNetwork(String name, String subnet, String gateway, String ipRange) {
        DockerNetworkRecord containerRecord = DataHub
                .useContext()
                .newRecord(DOCKER_NETWORK)
                .setName(name)
                .setSubnet(subnet)
                .setGateway(gateway)
                .setIpRange(ipRange);
        return containerRecord.insert() == 1 ? containerRecord : null;
    }

    public DockerNetworkRecord getNetworkById(Long id) {
        return DataHub.useContext().fetchOne(DOCKER_NETWORK.where(DOCKER_NETWORK.ID.eq(id)));
    }

    public List<DockerNetworkRecord> getNetworkByIds(List<Long> ids) {
        return DataHub.useContext().fetch(DOCKER_NETWORK.where(DOCKER_NETWORK.ID.in(ids)));
    }
}
