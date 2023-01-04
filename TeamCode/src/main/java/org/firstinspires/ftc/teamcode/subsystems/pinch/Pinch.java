package org.firstinspires.ftc.teamcode.subsystems.pinch;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Pinch {

    private static Servo servo0;
    private static Servo servo1;



    public static void init(HardwareMap hardwareMap) {
        servo0 = hardwareMap.get(Servo.class, "0");
        servo1 = hardwareMap.get(Servo.class, "1");
    }
    private static boolean isPinchState = false;


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
    servo0.setPosition( PinchConstants.servo0OpenPos);
    servo1.setPosition(PinchConstants.servo1OpenPos);

}
public static void closePinch(){
    servo0.setPosition( PinchConstants.servo0ClosePos);
    servo1.setPosition( PinchConstants.servo1ClosePos);



}
public static boolean isFinishedMoving(){
        return isPinchState;
}
}