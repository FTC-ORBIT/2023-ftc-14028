package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class DriveTrain {

    private static DcMotor lf;
    private static DcMotor rf;
    private static DcMotor lb;
    private static DcMotor rb;

    public static void init(HardwareMap hardwareMap) {
        lf = hardwareMap.get(DcMotor.class, "lf");
        rf = hardwareMap.get(DcMotor.class, "rf");
        lb = hardwareMap.get(DcMotor.class, "lb");
        rb = hardwareMap.get(DcMotor.class, "rb");

        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public static void operate(Gamepad gamepad) {
        lf.setPower(gamepad.left_stick_y + gamepad.left_stick_x - gamepad.left_trigger + gamepad.right_trigger);
        rf.setPower(gamepad.left_stick_y - gamepad.left_stick_x + gamepad.left_trigger - gamepad.right_trigger);
        lb.setPower(gamepad.left_stick_y - gamepad.left_stick_x - gamepad.left_trigger - gamepad.right_trigger);
        rb.setPower(gamepad.left_stick_y + gamepad.left_stick_x + gamepad.left_trigger - gamepad.right_trigger);
    }
}
