#!/bin/bash
export HOST_IP="$(ifconfig en0 2>/dev/null|awk '/inet / {print $2}'|sed 's/addr://')"

#read -p "Invoke Gradle Build y/n - Default n?" gr
#if [$gr == "y"] || [$gr = "Y"]
#then
#    echo "Building Project...."
#    gradle build
#    echo "Build Complete..."
#fi

echo "Building Project...."
gradle build
echo "Build Complete..."

echo "Invoking docker-compose: Deploy containers and start Application Service "
docker-compose up -d
echo "Containers & the Application are up and running..."
