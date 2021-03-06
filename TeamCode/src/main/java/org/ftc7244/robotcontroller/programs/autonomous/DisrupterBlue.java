package org.ftc7244.robotcontroller.programs.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.ftc7244.robotcontroller.autonomous.PIDAutonomous;
import org.ftc7244.robotcontroller.autonomous.drivers.EncoderDrive;

@Autonomous(name = "Disrupter Blue", group = "Blue")
public class DisrupterBlue extends PIDAutonomous {

    @Override
    public void run() throws InterruptedException {
        //Move off the wall and head in the direction of the line
        gyroscope.drive(.5, 5);
        gyroscope.rotate(45);
        //Drive to the middle of the field
        gyroscope.drive(.5, 8);
        //Orient for shooting balls
        gyroscope.rotate(100);
        //shoot two balls
        robot.shootLoop(2, 500);

        long currentDiff;
        do {
            currentDiff = getAutonomousEnd() - System.currentTimeMillis();
            idle();
        } while (currentDiff > 19500);

        //rotate back and drive onto the base
        gyroscope.drive(-1, 23);
        encoder.rotate(0.3, 70, EncoderDrive.Direction.RIGHT);
        gyroscope.resetOrientation();
        gyroscope.drive(1, 90);

        long diff;
        do {
            diff = getAutonomousEnd() - System.currentTimeMillis();
            idle();
        } while (diff > 2000);

        gyroscope.drive(-1, 40);
    }
}
