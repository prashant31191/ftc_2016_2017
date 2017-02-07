package org.ftc7244.robotcontroller.sensor;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by brandon on 2/7/17.
 */

public abstract class SensorProvider {

    /**
     * Begin running the sensor and based off the sampling period update the values.
     *
     * @param map the hardware map to obtain access of the sensor
     */
    public abstract void start(HardwareMap map);

    public abstract void stop();
}
