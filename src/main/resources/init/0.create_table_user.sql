-- vps_management.`user` definition
CREATE TABLE vps_management.`user` (
  `id` BIGINT(20) auto_increment NOT NULL,
  `email` varchar(64) NOT NULL,
  `password` varchar(100) NOT NULL,
  CONSTRAINT user_pk PRIMARY KEY (`id`),
  UNIQUE (`email`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
