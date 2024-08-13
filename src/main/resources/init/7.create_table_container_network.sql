-- vps_management.container_network definition
CREATE TABLE vps_management.`container_network` (
	`container_id` BIGINT NOT NULL,
	`network_id` BIGINT NOT NULL,
	CONSTRAINT container_network_container_fk FOREIGN KEY (container_id) REFERENCES vps_management.`docker_container`(`id`),
	CONSTRAINT container_network_network_fk FOREIGN KEY (network_id) REFERENCES vps_management.`docker_network`(`id`),
	CONSTRAINT container_network_pk PRIMARY KEY (container_id, network_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
