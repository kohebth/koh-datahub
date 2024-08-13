-- vps_management.user add user
INSERT INTO vps_management.`user`
(id, email, password)
VALUES(1, 'duy.nc164814@sis.hust.edu.vn', 'YTY2NWE0NTkyMDQyMmY5ZDQxN2U0ODY3ZWZkYzRmYjhhMDRhMWYzZmZmMWZhMDdlOTk4ZTg2ZjdmN2EyN2FlMw==');

-- vps_management.docker_container add container
INSERT INTO vps_management.docker_container
(id, name, status)
VALUES(1, 'duy-nc164814-ubuntu', 'DOWN');

-- vps_management.docker_network add image
INSERT INTO vps_management.docker_image
(id, name, repo, version, docker_id)
VALUES(1, 'custom-ubuntu', '', '22.04', '4858bd0df37e');

-- vps_management.docker_network add network
INSERT INTO vps_management.docker_network
(id, name, subnet, gateway, ip_range)
VALUES(1, 'vps-network', '172.28.0.0/16', '172.28.0.1', '172.28.0.0/24');

-- vps_management.docker_volume add mount point
INSERT INTO vps_management.docker_volume
(id, name, host, virtual)
VALUES(1, 'duync164814_home', '/home/duync/pgit/vps-docker/.docker_cache', '/home/duync164814');

-- vps_management.container_image add mount point
INSERT INTO vps_management.container_image
(container_id, image_id)
VALUES(1, 1);

-- vps_management.container_network add mount point
INSERT INTO vps_management.container_network
(container_id, network_id)
VALUES(1, 1);

-- vps_management.container_volume add mount point
INSERT INTO vps_management.container_volume
(container_id, volume_id)
VALUES(1, 1);;

