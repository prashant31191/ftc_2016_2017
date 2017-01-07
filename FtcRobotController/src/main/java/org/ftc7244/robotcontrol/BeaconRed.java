package org.ftc7244.robotcontrol;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.RobotLog;

import org.ftc7244.robotcontrol.autonomous.BeaconAutonomous;
import org.ftc7244.robotcontrol.autonomous.PIDAutonomous;

/**
 * Created by OOTB on 11/12/2016.
 */
//
@Autonomous(name="Beacon Red")
public class BeaconRed extends BeaconAutonomous {

    @Override
    public void run() throws InterruptedException {
        drive(-0.35, 16);
        sleep(1000);
        robot.shootLoop(2, 1500);
        drive(-0.35, 21.5);
        rotate(-43);
        drive(-0.35, 2);

        sleep(2000);
        if (isColor(Color.RED)){
            pushBeacon();
        } else {
            drive(-0.35, 5);
            pushBeacon();
        }

        rotate(90);
        robot.getIntake().setPower(1);
        drive(.75, 40);
        sleep(2000);
        drive(.75, 10);
    }

}