#!/bin/bash

# Do a maven release of the current version and ask for the new version number.
mvn release:clean release:prepare release:perform && mvn clean && git push
