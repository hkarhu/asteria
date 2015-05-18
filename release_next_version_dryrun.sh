#!/bin/bash

# Test the maven release process without actually creating a release.
mvn release:prepare -DdryRun=true

