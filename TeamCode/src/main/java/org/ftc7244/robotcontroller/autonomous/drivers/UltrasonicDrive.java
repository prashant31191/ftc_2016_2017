package org.ftc7244.robotcontroller.autonomous.drivers;

import org.ftc7244.robotcontroller.Westcoast;
import org.ftc7244.robotcontroller.autonomous.controllers.PIDControllerBuilder;
import org.ftc7244.robotcontroller.autonomous.controllers.PIDDriveControl;
import org.ftc7244.robotcontroller.autonomous.terminators.ConditionalTerminator;
import org.ftc7244.robotcontroller.autonomous.terminators.SensitivityTerminator;
import org.ftc7244.robotcontroller.autonomous.terminators.TimerTerminator;

/**
 * Because of limitations in the ultrasonic sensor moving and reading the values is not possible.
 * However it is still used to rotate to be parallel with the wall.
 */
public class UltrasonicDrive extends PIDDriveControl {

    private static final double OFFSET_LEADING = 0.0407, OFFSET_TRAILING = 0;

    /**
     * Incorporates the default PID tunings but allows for debugging within the code. Additionally,
     * it has deadbands for the integral and a limit for the PID output. It is fairly simple and
     * uses the ultrasonic sensors from the robot class.
     *
     * @param robot access to the ${@link Westcoast#getLeadingUltrasonic()} and ${@link Westcoast#getTrailingUltrasonic()} ()}
     */
    public UltrasonicDrive(Westcoast robot) {
        super(new PIDControllerBuilder()
                        .setProportional(0.15)
                        .setIntegral(0.0005)
                        .setDelay(30)
                        .setIntegralRange(0.75)
                        .setIntegralReset(true)
                        .setOutputRange(.22)
                        .createController(),
                robot);
    }

    @Override
    public double getReading() {
        double leading = robot.getLeadingUltrasonic().getUltrasonicLevel() - OFFSET_LEADING;
        double trailing = robot.getTrailingUltrasonic().getUltrasonicLevel() - OFFSET_TRAILING;

        return leading - trailing;
    }

    /**
     * Makes the robot parallel using the ultrasonic sensors however it will only run for 5
     * seconds before terminating. It expects to be within .1 of an inch of the wall for 200
     * milliseconds before it can continue
     *
     * @throws InterruptedException if code fails to terminate on stop requested
     */
    public void parallelize() throws InterruptedException {
        control(0, 0, new ConditionalTerminator(new SensitivityTerminator(this, 0, 0.08, 90), new TimerTerminator(5000)));
    }
}
