docker stop node_1
docker rm node_1
docker stop node_2
docker rm node_2
docker compose -f server_node/docker-compose.yml up -d
docker exec -it node_1 sh -c "/opt/ignite/apache-ignite/bin/control.sh --set-state ACTIVE --yes"