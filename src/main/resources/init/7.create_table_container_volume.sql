-- vps_management.container_volume definition
CREATE TABLE vps_management.`container_volume` (
	`container_id` BIGINT NOT NULL,
	`volume_id` BIGINT NOT NULL,
	CONSTRAINT container_volume_container_fk FOREIGN KEY (container_id) REFERENCES vps_management.`docker_container`(`id`),
	CONSTRAINT container_volume_volume_fk FOREIGN KEY (volume_id) REFERENCES vps_management.`docker_volume`(`id`),
	CONSTRAINT container_volume_pk PRIMARY KEY (container_id, volume_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
