#!/bin/bash

# Create an executable jar file
mkdir sticky_note 
javac -d sticky_note RunApplication.java
(cd sticky_note; echo "Main-Class: RunApplication" > manifest.mf; jar -cmf manifest.mf runSN.jar *.class)

# Create .desktop file for the Sticky Note application
# ****** The full path to the img/directory should be placed here
(cd sticky_note; echo -e "[Desktop Entry]\nEncoding=UTF-8\nVersion=1.0\nType=Application\nName=My Very Sticky Notes" > stickyNote.desktop;
echo -e "Exec=java -jar [*******]/sticky_note/runSN.jar\nIcon=[*******]/img.png\nTerminal=false" >> stickyNote.desktop; chmod +x stickyNote.desktop)

# The "Terminal" entry for the .desktop file should always be placed at the end of the file (if you decided to include the entry) 