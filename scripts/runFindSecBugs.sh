#!/usr/bin/env bash

source scripts/requireCommand.sh
requireCommand docker

# The owasp project must be compiled since findsecbugs works with .war archives
mvn compile

benchmark_version=$(scripts/getBenchmarkVersion.sh)
findsecbugs_version=1.13.0
result_file="/src/results/Benchmark_$benchmark_version-FindSecBugs-v$findsecbugs_version.xml"

docker run --rm -v $(pwd):/src ocelaiwo/findsecbugs:1.13.0 sh -c "/findsecbugs-cli-1.13.0/findsecbugs.sh -xml /src/target/benchmark.war > $result_file"
