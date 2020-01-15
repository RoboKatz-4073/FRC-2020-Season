package frc.robot.commands;

/**
* This is the class that controlls the R2-D2 beeping of our robot
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

import java.util.Random;
import java.util.concurrent.TimeUnit;

import frc.robot.util.RaspberryPi;
import frc.robot.util.RaspiCOMM;

public class R2D2 {

    // Username and password for the r2d2 user on the pi
    private final String userName = "r2d2";
    private final String password = "Team4073!";

    // Pi communication class
    public RaspiCOMM pi;

    // Thread to keep beeping controll separated
    private Thread r2d2;

    // Constructor
    public R2D2(RaspberryPi pi) {

        // Modify pi config for user
        pi.setUser(userName);
        pi.setPassword(password);

        // Establish connection
        this.pi = new RaspiCOMM(pi);

        // Beep-beep thread
        r2d2 = new Thread(() -> {

            // Creates object for random integer
            Random rand = new Random(30);

            // Loop
            while (r2d2.isAlive()) {

                // Start beep-booooop
                beepboop();

                try {

                    // Wait
                    TimeUnit.SECONDS.wait((long) rand.nextInt());

                } catch (InterruptedException e) {

                }

            }

        });

        // Enable thread
        r2d2.run();

    }

    public void stop() {

        // Kill thread
        r2d2.interrupt();

    }

    private void beepboop() {
    }

}