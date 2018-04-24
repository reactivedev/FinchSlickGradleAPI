#!/bin/bash
export HOST_IP="$(ifconfig en0 2>/dev/null|awk '/inet / {print $2}'|sed 's/addr://')"
docker-compose down
docker-compose rm -f