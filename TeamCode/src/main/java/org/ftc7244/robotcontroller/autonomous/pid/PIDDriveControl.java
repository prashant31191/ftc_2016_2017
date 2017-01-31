package org.ftc7244.robotcontroller.autonomous.pid;

import com.qualcomm.robotcore.util.RobotLog;

import org.ftc7244.robotcontroller.Westcoast;
import org.ftc7244.robotcontroller.autonomous.Status;
import org.ftc7244.robotcontroller.autonomous.pid.terminators.TerminationMode;
import org.ftc7244.robotcontroller.autonomous.pid.terminators.Terminator;

/**
 * Created by OOTB on 1/15/2017.
 */

public abstract class PIDDriveControl {

    protected boolean debug;
    protected PIDController controller;
    protected Westcoast robot;

    public PIDDriveControl(PIDController controller, Westcoast robot) {
        this(controller, robot, false);
    }

    public PIDDriveControl(PIDController controller, Westcoast robot, boolean debug) {
        this.controller = controller;
        this.robot = robot;
        this.debug = debug;
    }

    public abstract double getReading();

    protected void control(double target, double powerOffset, Terminator terminator) throws InterruptedException {
        controller.reset();
        controller.setTarget(target);
        boolean shouldTerminate = false;
        do {
            double pid = controller.update(getReading());
            if (debug) {
                RobotLog.ii("PID",
                        "|" + controller.getProportional() * controller.getkP() +
                                "|" + controller.getIntegral() * controller.getkI() +
                                "|" + controller.getDerivative() * controller.getkD() +
                                "|" + getReading());
            }
            robot.getDriveLeft().setPower(powerOffset + pid);
            robot.getDriveRight().setPower(powerOffset - pid);
        } while (!terminator.shouldTerminate() && !Thread.interrupted() && !Status.isStopRequested());

        robot.getDriveLeft().setPower(0);
        robot.getDriveRight().setPower(0);
    }
}
