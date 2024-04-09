#!/usr/bin/env bash

echo "Running Semgrep"

benchmark_version=$(scripts/getBenchmarkVersion.sh)
semgrep_version=$(semgrep --version)
result_file="results/Benchmark_$benchmark_version-Semgrep-v$semgrep_version.json"

semgrep --config p/security-audit -q --json --include="src/main/java/org/owasp/benchmark/testcode/*.java" -o "$result_file" . > /dev/null
