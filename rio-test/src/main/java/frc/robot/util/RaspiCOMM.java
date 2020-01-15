package frc.robot.util;

/**
* This is the class for communication with our various Raspberry Pi's
*
* Please feel free to modify, share, and revise all code in this class
* provided our name (FRC 4073), and all authors below are credited.
*
* @author josephtelaak
* 
* @date_created 1/15/2020
* @date_modified 1/15/2020
*
* @revision 01
**/

public class RaspiCOMM {

    private RaspberryPi pi;
    
    public RaspiCOMM(RaspberryPi pi) {

        this.pi = pi;

    }

    public RaspiCOMM(String userName, String password, String ipaddress) {

        // Create Raspberry Pi object
        pi = new RaspberryPi(userName, password, ipaddress);

    }

    public RaspiCOMM(String userName, String password, String ipaddress, int SSH_Port) {

        // Create Raspberry Pi object
        pi = new RaspberryPi(userName, password, ipaddress, SSH_Port);

    }
}

