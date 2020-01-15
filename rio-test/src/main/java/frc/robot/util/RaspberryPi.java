package frc.robot.util;

/**
* Object for the Raspberry Pi's
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

public class RaspberryPi {

    // Login info
    public String userName;
    public String password;

    // Connection information
    public String ipaddress;
    public int SSH_Port = 22;
    
    public String URL;

    public RaspberryPi (String userName, String password, String ipaddress) {

        // Create object
        this.userName = userName;
        this.password = password;
        this.ipaddress = ipaddress;

        URL = ipaddress;

    }

    public RaspberryPi (String userName, String password, String ipaddress, int SSH_Port) {

        // Create object
        this.userName = userName;
        this.password = password;
        this.ipaddress = ipaddress;
        this.SSH_Port = SSH_Port;

        URL = ipaddress = ":" + SSH_Port;

    }

    public String toString() {
        
        // Output identification information
        return "RaspberryPi # IPAddress: " + ipaddress + ":" + SSH_Port + " User: "+ userName;

    }

    public void setUser(String userName) {

        // Alter username
        this.userName = userName;

    }

    public void setPassword(String password) {

        // Alter password
        this.password = password;

    }

    // Return information
    public String getUser() { return userName; }
    public String getIPAddress() { return ipaddress; }
    public String getPassword() { return password; }
    public String getURL() { return URL; }

    public int getPort() { return SSH_Port; }

}