#!/usr/bin/env bash

source scripts/requireCommand.sh

requireCommand docker

docker pull ocelaiwo/codeql-java:2.16.5

benchmark_version=$(scripts/getBenchmarkVersion.sh)
codeql_version=$(docker run --rm ocelaiwo/codeql-java:2.16.5 /codeql-bundle-linux64/codeql/codeql --version -q)
result_file="/src/results/Benchmark_$benchmark_version-Code-v$codeql_version.json"

docker run --rm -v $(pwd):/benchmark ocelaiwo/codeql-java:2.16.5 sh -c "cd benchmark; /codeql-bundle-linux64/codeql/codeql database create owasp-benchmark --language=java"

docker run --rm -v $(pwd):/benchmark ocelaiwo/codeql-java:2.16.5 sh -c "cd benchmark; /codeql-bundle-linux64/codeql/codeql database analyze owasp-benchmark java-code-scanning.qls --format=sarifv2.1.0 --output=results/Benchmark_1.2-codeql_java-code-scanning_qls.sarif"

docker run --rm -v $(pwd):/benchmark ocelaiwo/codeql-java:2.16.5 sh -c "cd benchmark; rm -rf owasp-benchmark"
docker run --rm -v "${PWD}:/src" ubuntu sh -c "chown $(id -u $USER):$(id -g $USER) -R /src"
