package org.ftc7244.robotcontroller.programs.autonomous;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.ftc7244.robotcontroller.autonomous.PIDAutonomous;
import org.ftc7244.robotcontroller.autonomous.drivers.GyroscopeDrive;

/**
 * Created by FTC 7244 on 3/31/2017.
 */
public class ReverseBeaconRed extends PIDAutonomous {

    @Override
    public void run() throws InterruptedException {
        gyroscope.drive(-0.75, 90, -38);
        sleep(500);
        gyroscope.rotate(38);
        ultrasonic.parallelize();
        sleep(300);
        gyroscope.resetOrientation();
        gyroscope.driveUntilLine(-0.2, GyroscopeDrive.Sensor.Trailing);
        sleep(150);
        if (robot.isColor(Color.RED)) {
            //if it is in front of blue immediately press the button
            robot.pushBeacon();
        } else {
            //if it is red drive forward then press the button
            gyroscope.drive(-0.2, 2.5);
            robot.pushBeacon();
        }

        gyroscope.driveUntilLine(0.25, GyroscopeDrive.Sensor.Leading, 0, 35, 55);
        sleep(500);
        if (robot.isColor(Color.RED)) {
            //if it is in front of blue immediately press the button
            robot.pushBeacon();
        } else {
            //if it is red drive forward then press the button
            gyroscope.drive(0.2, 5);
            robot.pushBeacon();
        }

        gyroscope.rotate(-40);
        gyroscope.drive(0.5, 30);
        robot.shootLoop(2, 500);

    }
}
