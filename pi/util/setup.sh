#!/bin/bash

# Base configuration installer for Team 4073's Camera Raspberry Pi 3's
# Written by Joseph Telaak

# Initial upgrade and install
sudo apt-get update
sudo apt-get upgrade -y

# Install utils
sudo apt-get install unzip zip -y

# Unzip
unzip main.zip

# Motd
cd camera
sudo mv motd /etc/

# Base program installs
sudo apt-get install apache2 -y

# Motion
#cd /
#cd etc
#mkdir motion-tmp
#cd motion-tmp
#
#sudo apt-get install -y libjpeg62 libjpeg62-dev libavformat53 libavformat-dev libavcodec53 libavcodec-dev
#sudo apt-get install -y libavutil51 libavutil-dev libc6-dev zlib1g-dev libmysqlclient18 libmysqlclient-dev libpq5 libpq-dev
#
#sudo wget https://www.opsactive.com/wp-content/uploads/2013/11/motion-mmal.tar.gz
#sudo tar zxvf motion-mmal.tar.gz
#
#sudo apt-get install wput -y
#
#sudo mv motion-mmalcam.conf /etc/motion.conf
#sudo mv motion /usr/bin

# Install Java
sudo add-apt-repository ppa:webupd8team/java

sudo apt-get update
sudo apt-get upgrade -y

sudo apt-get install oracle-java9-installer -y
sudo apt install oracle-java9-set-default -y

# Done
clear
echo "Done!"
echo
#echo "Add - motion - to /etc/rc.local"
echo
echo "Thanks for installing the base camera platform for Team 4073"
echo "Written by Joseph Telaak 2020"
