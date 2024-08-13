-- vps_management.`session` definition
CREATE TABLE vps_management.`session` (
	`id` BIGINT(20) auto_increment NOT NULL,
	`user_id` BIGINT(20) NOT NULL,
	`session_id` varchar(128) NOT NULL,
	`expire_time` timestamp NOT NULL,
	CONSTRAINT session_pk PRIMARY KEY (id),
	CONSTRAINT session_user_fk FOREIGN KEY (user_id) REFERENCES vps_management.`user`(id),
	UNIQUE (`session_id`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
