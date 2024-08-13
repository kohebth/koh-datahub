-- vps_management.docker_image definition
CREATE TABLE vps_management.`docker_image` (
	`id` BIGINT auto_increment NOT NULL,
	`name` varchar(100) NOT NULL,
	`repo` varchar(100) NOT NULL,
	`version` varchar(24) NOT NULL,
	CONSTRAINT `docker_image_pk` PRIMARY KEY (id),
	UNIQUE (`name`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
