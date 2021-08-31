#!/bin/bash

source_code_path=$(pwd)
app_container="app_container"

set -e

mkdir $app_container
javac -d $app_container RunApplication.java
cd $app_container

# Create an executable jar file
echo "Main-Class: RunApplication" > manifest.mf
jar -cmf manifest.mf runSN.jar *.class

# Create .desktop file for the Sticky Note application
echo -e "[Desktop Entry]\nEncoding=UTF-8\nVersion=1.0\nType=Application\nName=My Very Sticky Notes" > stickyNote.desktop
echo -e "Exec=java -jar ${source_code_path}/${app_container}/runSN.jar\nIcon=${source_code_path}/img.png\nTerminal=false" >> stickyNote.desktop
chmod +x stickyNote.desktop

# The "Terminal" entry for the .desktop file should always be placed at the end of the file (if you decided to include the entry) 
