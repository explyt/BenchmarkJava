#!/usr/bin/env bash

# Check for install/updates at https://github.com/ZupIT/horusec

source scripts/requireCommand.sh
requireCommand docker

# Make sure we're using most recent version
docker pull horuszup/horusec-cli:v2.7.0

benchmark_version=$(scripts/getBenchmarkVersion.sh)
horusec_version=$(docker run --rm horuszup/horusec-cli:v2.7.0 horusec version 2>&1 | grep Version | awk '{print $NF}')

result_file="src/results/Benchmark_$benchmark_version-horusec-$horusec_version.json"
docker run --rm \
          -v /var/run/docker.sock:/var/run/docker.sock \
          -v "$(pwd)":/src horuszup/horusec-cli:v2.7.0 \
          horusec start -p /src -P "$(pwd)" -t 3600 \
          -i='results/*,scorecard/*,scripts/*' \
          -o="json" -O="$result_file"
