#!/usr/bin/env bash

echo "Running CodeQL"

benchmark_version=$(scripts/getBenchmarkVersion.sh)
codeql_version=$($HOME/.local/bin/codeql/codeql --version -q)
result_file="results/Benchmark_$benchmark_version-CodeQL-v$codeql_version.sarif"

$HOME/.local/bin/codeql/codeql database create owasp-benchmark --language=java --threads=0

$HOME/.local/bin/codeql/codeql database analyze owasp-benchmark java-code-scanning.qls --format=sarifv2.1.0 --threads=0 --output=$result_file

rm -rf owasp-benchmark
