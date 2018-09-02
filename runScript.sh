#!/bin/bash

# Create an executable jar file
mkdir sticky_note 
javac -d sticky_note RunApplication.java
cp img.png sticky_note
cd sticky_note
echo "Main-Class: RunApplication" > manifest.mf
jar -cmf manifest.mf runSN.jar *.class

# Create .desktop file for the SN application
# ************* The full path to the new created dir should be placed here
echo -e "Encoding=UTF-8\nVersion=1.0 \nType=Application\nName=My very Sticky Notes\nTerminal=false" > SNapp.desktop
echo -e "Exec=java -jar [***********]/sticky_note/runSN.jar\nIcon=[*************]/sticky_note/img.png" >> SNapp.desktop
chmod +x SNapp.desktop