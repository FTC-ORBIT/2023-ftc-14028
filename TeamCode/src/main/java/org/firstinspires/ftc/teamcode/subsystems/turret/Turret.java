package org.firstinspires.ftc.teamcode.subsystems.turret;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PID;

public class Turret {
    private static DcMotor motor;

    private static final PID turretPID = new PID(TurretConstants.turretKp, TurretConstants.turretKi, TurretConstants.turretKd, TurretConstants.turretKf, TurretConstants.turretIzone);


    public static void init(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "turret");
        resetEncoder();
    }

    public static void operate(Gamepad gamepad) {

        double gamepadChange = -gamepad.right_stick_x;

        if (motor.getCurrentPosition() < 450 && gamepad.right_stick_x > 0) {

            gamepadChange = 0;
        }


        if (motor.getCurrentPosition() > 2750 && gamepad.right_stick_x < 0) {

            gamepadChange = 0;

        }
            motor.setPower(gamepadChange);


    }

    public static double getAngle() {
        return motor.getCurrentPosition();
//                * org.firstinspires.ftc.teamcode.subsystems.turret.TurretConstants.ticksToAngle;
    }


    public static boolean isFinishedMoving = false;

    public static void setAngle(double wantedAngle) {


        turretPID.setWanted(wantedAngle);
        motor.setPower(turretPID.update(getAngle()));

isFinishedMoving = Math.abs(motor.getCurrentPosition()) > Math.abs(wantedAngle);
    }

    public static boolean isFinishedMoving() {
        return isFinishedMoving;
    }

    public static void resetEncoder(){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
}
