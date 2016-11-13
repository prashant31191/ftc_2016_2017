package org.ftc7244.robotcontrol;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.ftc7244.robotcontrol.autonomous.BeaconBaseAutonomous;

/**
 * Created by OOTB on 11/12/2016.
 */
@Autonomous(name="Beacon Blue")
public class BeaconBlue extends BeaconBaseAutonomous {
    @Override
    public void run() throws InterruptedException {
        drive(0.4, 8.5, 0);
        drive(1.0, 26, 21);

        robot.shootLoop(2, 750);

        //Align ourselves with the wall
        drive(1.0, 25, 25);
        drive(1.0, 15, 0);
        drive(1.0, 10, 10);
        drive(1.0, -3, -3);
        rotate(.8, 57, Direction.RIGHT);

        drive(1.0, 2, 2);
        pushBeaconUntil(BeaconColor.BLUE);
    }
}