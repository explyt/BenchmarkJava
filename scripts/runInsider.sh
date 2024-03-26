#!/usr/bin/env bash

# Check for install/updates at https://github.com/insidersec/insider

source scripts/requireCommand.sh

requireCommand docker

benchmark_version=$(scripts/getBenchmarkVersion.sh)
insider_version=3.0.0 # We use docker tag 3.0.0, for some reason insider's -version option does something weird
result_file="results/Benchmark_$benchmark_version-insider-v$insider_version.json"

docker run --entrypoint /bin/sh --rm -v $(pwd):/target-project insidersec/insider:3.0.0 -c "./insider -tech java -exclude '.idea' -exclude '.mvn' -exclude 'results' -exclude 'scorecard' -exclude 'scripts' -exclude 'tools' -target /target-project; cp report.json /target-project/$result_file"

docker run --rm -v "${PWD}:/src" ubuntu sh -c "chown $(id -u $USER):$(id -g $USER) -R /src"
