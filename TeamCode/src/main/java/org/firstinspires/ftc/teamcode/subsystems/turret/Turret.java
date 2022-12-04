package org.firstinspires.ftc.teamcode.subsystems.turret;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Turret {
    private static DcMotor motor;

    public static void init(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "turret");

    }

    public static void operate(Gamepad gamepad) {

        motor.setPower(gamepad.right_stick_x);

    }


}
