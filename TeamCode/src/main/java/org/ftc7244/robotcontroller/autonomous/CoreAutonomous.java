package org.ftc7244.robotcontroller.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by OOTB on 1/23/2017.
 */

public abstract class CoreAutonomous extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Status.setAutonomous(this);
        try {
            run();
        } finally {
            Status.setAutonomous(null);
        }
    }

    public abstract void run() throws InterruptedException;
}