-- vps_management.docker_container definition
CREATE TABLE vps_management.`docker_container` (
	`id` BIGINT auto_increment NOT NULL,
	`name` varchar(100) NOT NULL,
	`memory` BIGINT NOT NULL,
	`status` ENUM ('DOWN', 'UP', 'ERROR') NOT NULL,
	CONSTRAINT docker_container_pk PRIMARY KEY (id),
	UNIQUE (`name`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
