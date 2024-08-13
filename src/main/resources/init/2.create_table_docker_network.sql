-- vps_management.docker_network definition
CREATE TABLE vps_management.`docker_network` (
	`id` BIGINT auto_increment NOT NULL,
	`name` varchar(100) NOT NULL,
	`subnet` varchar(24) NOT NULL,
	`gateway` varchar(24) NOT NULL,
	`ip_range` varchar(24) NOT NULL,
	CONSTRAINT docker_network_pk PRIMARY KEY (id),
	UNIQUE (`name`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
