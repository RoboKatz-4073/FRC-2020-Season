package frc.robot.util;

import java.io.IOException;

/**
* This is the class for communication with our various Raspberry Pi's
*
* Please feel free to modify, share, and revise all code in this class
* provided our name (FRC 4073), and all authors below are credited.
*
* @author josephtelaak
* 
* @date_created 1/15/2020
* @date_modified 1/16/2020
*
* @revision 01
**/

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.TransportException;

public class RaspiCOMM {

    private SSHClient ssh;
    private Session session;

    private RaspberryPi pi;

    public RaspiCOMM(RaspberryPi pi) {

        // Create object
        this.pi = pi;

        // Establish Connection
        initialize();
    }

    public RaspiCOMM(String userName, String password, String ipaddress) {

        // Create Raspberry Pi object
        pi = new RaspberryPi(userName, password, ipaddress);

        // Establish connection
        initialize();

    }

    public RaspiCOMM(String userName, String password, String ipaddress, int SSH_Port) {

        // Create Raspberry Pi object
        pi = new RaspberryPi(userName, password, ipaddress, SSH_Port);

        // Establish connection
        initialize();

    }

    // Establish ssh session
    private void initialize() {

        // Create client
        ssh = new SSHClient();

        // Load information and connect
        try {

            ssh.loadKnownHosts();
            ssh.connect(pi.userName, pi.SSH_Port);
            ssh.authPassword(pi.ipaddress, pi.password);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    // Disconnect from ssh session
    public void disconnect() {

        // Disconnect Session
        try {

            session.close();
            ssh.disconnect();
            
        } catch (TransportException | ConnectionException e) {
           
            e.printStackTrace();
        
        } catch (IOException e) {
            
            e.printStackTrace();
            
        }

        

    }

    // Pass through commands
    public void sendCommand(String command) {

        // Run command
        try {

            session.exec(command);

        } catch (ConnectionException | TransportException e) {

            e.printStackTrace();

        }

    }
}
