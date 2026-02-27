#!/bin/bash

# Function to handle script exit
cleanup_on_exit() {
    echo "Stopping cleanup process..."
    if [ -n "$CLEANUP_PID" ]; then
        kill "$CLEANUP_PID" 2>/dev/null
    fi
    exit
}

# Trap interrupts (Ctrl+C) to stop the cleanup background process
trap cleanup_on_exit SIGINT SIGTERM EXIT

# Start cleanup loop in background
echo "Starting background cleanup of metadata files (._*) and files ending in underscore (*_)..."
(
    while true; do
        # Find and delete files starting with ._ OR files ending with _
        # Excluding files inside .git to avoid corrupting git database if any match
        find . -path "./.git" -prune -o \( -name "._*" -o -name "*_" \) -type f -exec rm -f {} +
        sleep 2
    done
) &
CLEANUP_PID=$!

# Start Spring Boot application
echo "Starting Spring Boot application..."
./mvnw spring-boot:run
