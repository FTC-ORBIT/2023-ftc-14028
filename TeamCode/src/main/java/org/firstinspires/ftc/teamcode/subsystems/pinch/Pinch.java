package org.firstinspires.ftc.teamcode.subsystems.pinch;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Pinch {
    static Servo servo0;
    static Servo servo1;


    public static void init(HardwareMap hardwareMap) {
        servo0 = hardwareMap.get(Servo.class, "0");
    }


    public static void operate(pinchState state) {
        switch (state) {

            case OPEN:
                break;
            case CLOSE:

                break;
        }

    }

}