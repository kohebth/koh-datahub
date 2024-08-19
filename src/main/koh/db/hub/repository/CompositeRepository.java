package koh.db.hub.repository;

import koh.db.hub.DataHub;
import lombok.Data;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Select;
import org.jooq.impl.DSL;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeRepository extends AbstractRepository {
    public ContainerSetup getContainerSetup(Long containerId) {
        Field<Long> userIdField = USER_CONTAINER.USER_ID.as("user_id");
        Field<Long> containerIdField = USER_CONTAINER.CONTAINER_ID.as("container_id");
        Field<Long> imageIdField = CONTAINER_IMAGE.IMAGE_ID.as("image_id");
        Field<String> volumeIdsField = DSL.groupConcatDistinct(CONTAINER_VOLUME.VOLUME_ID).as("volume_ids");
        Field<String> networkIdsField = DSL.groupConcatDistinct(CONTAINER_NETWORK.NETWORK_ID).as("network_ids");

        Select<?> query = DataHub
                .useContext()
                .select(userIdField, containerIdField, imageIdField, volumeIdsField, networkIdsField)
                .from(USER_CONTAINER)
                .leftJoin(CONTAINER_IMAGE)
                .on(USER_CONTAINER.CONTAINER_ID.eq(CONTAINER_IMAGE.CONTAINER_ID))
                .leftJoin(CONTAINER_NETWORK)
                .on(USER_CONTAINER.CONTAINER_ID.eq(CONTAINER_NETWORK.CONTAINER_ID))
                .leftJoin(CONTAINER_VOLUME)
                .on(USER_CONTAINER.CONTAINER_ID.eq(CONTAINER_VOLUME.CONTAINER_ID))
                .where(USER_CONTAINER.CONTAINER_ID.eq(DSL.value(containerId)))
                .groupBy(USER_CONTAINER.USER_ID, USER_CONTAINER.CONTAINER_ID, CONTAINER_IMAGE.IMAGE_ID);

        Record result = query.fetchOne();

        if (result != null) {
            return result.map(r -> new ContainerSetup(
                    r.get(userIdField),
                    r.get(containerIdField),
                    r.get(imageIdField),
                    Arrays.stream(r.get(volumeIdsField).split(",")).map(Long::parseLong).collect(Collectors.toList()),
                    Arrays.stream(r.get(networkIdsField).split(",")).map(Long::parseLong).collect(Collectors.toList())
            ));
        }
        return null;
    }

    @Data
    public static class ContainerSetup {
        final Long userId;
        final Long imageId;
        final Long containerId;
        final List<Long> volumeIds;
        final List<Long> networkIds;
    }
}
