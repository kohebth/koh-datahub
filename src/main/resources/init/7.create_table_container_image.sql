-- vps_management.container_image definition
CREATE TABLE vps_management.`container_image` (
	`container_id` BIGINT NOT NULL,
	`image_id` BIGINT NOT NULL,
	CONSTRAINT container_image_container_fk FOREIGN KEY (container_id) REFERENCES vps_management.`docker_container`(`id`),
	CONSTRAINT container_image_image_fk FOREIGN KEY (image_id) REFERENCES vps_management.`docker_image`(`id`),
	CONSTRAINT container_image_pk PRIMARY KEY (container_id, image_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
