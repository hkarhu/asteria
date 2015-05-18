#!/bin/bash

# Do maven release
mvn release:clean release:prepare release:perform && mvn clean && git push
