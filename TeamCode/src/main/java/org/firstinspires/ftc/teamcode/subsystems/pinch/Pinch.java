package org.firstinspires.ftc.teamcode.subsystems.pinch;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.turret.Turret;

public class Pinch {
    //private Enum pinchState = isFinishedMoving ? PinchState.CLOSE : PinchState.OPEN;
    private static Servo servo0;
    private static Servo servo1;


    public static void init(HardwareMap hardwareMap) {
        servo0 = hardwareMap.get(Servo.class, "0");
        servo1 = hardwareMap.get(Servo.class, "1");
    }


    public static void operate(PinchState state) {
        switch (state) {
            case OPEN:
                openPinch();

                break;
            case CLOSE:
                closePinch();

                break;
        }

    }
    public static void openPinch() {
        servo0.setPosition(PinchConstants.servo0OpenPos);
        servo1.setPosition(PinchConstants.servo1OpenPos);

    }

    public static void closePinch() {
        servo0.setPosition(PinchConstants.servo0ClosePos);
        servo1.setPosition(PinchConstants.servo1ClosePos);

    }
    public static void testClaw (Gamepad gamepad){
        servo0.setPosition(gamepad.right_trigger);
        servo1.setPosition(gamepad.left_trigger);
    }

}