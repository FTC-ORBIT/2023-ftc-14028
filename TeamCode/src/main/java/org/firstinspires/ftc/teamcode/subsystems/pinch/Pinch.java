package org.firstinspires.ftc.teamcode.subsystems.pinch;

import android.widget.Switch;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotState;

public class Pinch {

    static Servo servo0;
    static Servo servo1;


    public static void init(HardwareMap hardwareMap) {
        servo0 = hardwareMap.get(Servo.class, "0");
    }


    public static void operate(pinchState state) {
        switch (state) {

            case OPEN:
                servo0.setPosition( PinchConstants.openPos1);
                break;
            case CLOSE:
                servo0.setPosition( PinchConstants.closePos1);
                break;
        }

    }

    public static void operate2(pinchState state) {
        switch (state) {

            case OPEN:
                servo1.setPosition(PinchConstants.openPos2);
                break;
            default:

            case CLOSE:
                    servo1.setPosition(PinchConstants.closePos2);

                break;
        }


    }
    public static void operateRobotState(RobotState state) {
        switch (state) {
            case TRAVEL:
                servo0.setPosition( PinchConstants.closePos1);
                servo1.setPosition(PinchConstants.closePos2);
                break;
            case INTAKE:
                servo0.setPosition( PinchConstants.openPos1);
                servo1.setPosition(PinchConstants.openPos2);
                break;
            case  DROP:
                servo0.setPosition( PinchConstants.openPos1);
                servo1.setPosition(PinchConstants.openPos2);
        }
    }

}