-- vps_management.docker_volume definition
CREATE TABLE vps_management.`docker_volume` (
	`id` BIGINT auto_increment NOT NULL,
	`name` varchar(64) NOT NULL,
	`host` varchar(256) NOT NULL,
	`virtual` varchar(256) NOT NULL,
	`size_in_mb` MEDIUMINT NOT NULL,
	`type` ENUM ('VOLUME', 'BIND', 'TMPFS'),
	CONSTRAINT docker_volume_pk PRIMARY KEY (`id`),
	UNIQUE (`name`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
