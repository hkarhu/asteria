#!/bin/bash

# Creates a release package
# Must be given the release version as a parameter (used to find the compiled release jar)
if [ -z "$1" ] ; then
    echo "Pass in the release version as the first parameter (used to find the compiled release jar)"
    exit 1
fi

GAME_NAME=asteria
GAME_VERSION=$1
GAME_DIR=$GAME_NAME-$GAME_VERSION
RELEASE_FILE_NAME=$GAME_DIR.zip
BUILD_DIR=release-package-$GAME_NAME-$GAME_VERSION
RELEASE_FILE_DIR=src/main/release_files

echo "Building release package $RELEASE_FILE_NAME:"

# Build the jar
mvn clean package

# Remove previous build dir and packaged release
rm -rf $BUILD_DIR
rm $RELEASE_FILE_NAME

# Create directory to build the release package in
mkdir $BUILD_DIR
mkdir $BUILD_DIR/$GAME_DIR

# Copy over release specific files
cp -r $RELEASE_FILE_DIR/* $BUILD_DIR/$GAME_DIR/

# Copy over assets
cp -r assets/ $BUILD_DIR/$GAME_DIR/assets/

# Copy over the readme
cp README.MD $BUILD_DIR/$GAME_DIR/

# Copy over the jar file with all dependencies included
cp target/$GAME_NAME-$GAME_VERSION-jar-with-dependencies.jar $BUILD_DIR/$GAME_DIR/$GAME_NAME.jar

# Zip the release
cd $BUILD_DIR
zip -r ../$RELEASE_FILE_NAME $GAME_DIR
cd ..

echo "Building of release package $RELEASE_FILE_NAME ended"
