-- vps_management.user_metadata definition
CREATE TABLE vps_management.`user_metadata` (
	`id` BIGINT auto_increment NOT NULL,
	`user_id` BIGINT NOT NULL,
	`name` varchar(100) NULL,
	`type` ENUM ('IMAGE', 'VOLUME', 'NETWORK', 'ENVIRONMENT') NOT NULL,
	`hidden` BOOLEAN NOT NULL default FALSE,
	`blob` BLOB NOT NULL,
    CONSTRAINT user_metadata_user_fk FOREIGN KEY (user_id) REFERENCES vps_management.`user`(`id`),
	CONSTRAINT user_metadata_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
