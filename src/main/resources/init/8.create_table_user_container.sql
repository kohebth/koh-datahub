-- vps_management.user_container definition
CREATE TABLE vps_management.`user_container` (
	`container_id` BIGINT NOT NULL,
	`user_id` BIGINT NOT NULL,
	CONSTRAINT user_container_user_fk FOREIGN KEY (user_id) REFERENCES vps_management.`user`(`id`),
	CONSTRAINT user_container_container_fk FOREIGN KEY (container_id) REFERENCES vps_management.`docker_container`(`id`),
	CONSTRAINT user_container_pk PRIMARY KEY (container_id, user_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
