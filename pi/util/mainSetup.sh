#!/bin/bash

# Installer for Team 4073's Camera Processor Raspberry Pi 4
# Written by Joseph Telaak

# Initial upgrade and install
sudo apt-get update
sudo apt-get upgrade -y

# Base program installs
sudo apt-get install apache2 -y

# Install utils
sudo apt-get install unzip zip -y
sudo apt-get install espeak -y
sudo apt-get install mplayer -y

# Unzip
unzip camera.zip

# Motd
cd camera
sudo mv motd /etc/
cd ~

# Install Java
sudo add-apt-repository ppa:webupd8team/java

sudo apt-get update
sudo apt-get upgrade -y

sudo apt-get install oracle-java9-installer -y
sudo apt install oracle-java9-set-default -y

# Install Python
sudo apt-get install python3

# Fun stuff
sudo apt-get install lolcat cmatrix c2048 -y

# Evillimiter
git clone https://github.com/bitbrute/evillimiter.git
cd evillimiter
sudo python3 setup.py install
cd ~

# Done
clear
echo "Done!"
echo
echo "Add - bash ~/talk.sh - to /etc/rc.local"
echo "Add - java -jar cameraProcessor.jar - to /etc/rc.local"
echc
echo "Fun stuff added: lolcat, cmatrix, and c2048"
echo
echo "Thanks for installing the camera processor for Team 4073"
echo "Written by Joseph Telaak 2020"

