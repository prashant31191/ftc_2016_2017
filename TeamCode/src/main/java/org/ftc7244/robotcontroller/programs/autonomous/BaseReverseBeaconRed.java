package org.ftc7244.robotcontroller.programs.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.ftc7244.robotcontroller.autonomous.drivers.EncoderDrive;

/**
 * Created by FTC 7244 on 4/10/2017.
 */
@Autonomous(name = "Beacon Red [REVERSE] [BASE]", group = "Red")
public class BaseReverseBeaconRed extends ReverseBeaconRed {

    @Override
    public void run() throws InterruptedException {
        super.run();
        gyroscope.rotate(-100);
        robot.getIntake().setPower(1);
        gyroscope.drive(0.5, 30);

        EncoderDrive.Direction direction = EncoderDrive.Direction.LEFT;
        while (getAutonomousEnd() - (System.currentTimeMillis() + 1250) > 0) {
            encoder.rotate(1, 20, direction);
            direction = direction == EncoderDrive.Direction.LEFT ? EncoderDrive.Direction.RIGHT : EncoderDrive.Direction.LEFT;
        }
        gyroscope.drive(0.5, 20);
    }
}
