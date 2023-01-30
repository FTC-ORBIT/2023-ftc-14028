package org.firstinspires.ftc.teamcode.subsystems.turret;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PID;

public class Turret {
    private static DcMotor motor;

    private static final PID turretPID = new PID(org.firstinspires.ftc.teamcode.subsystems.turret.TurretConstants.turretKp, org.firstinspires.ftc.teamcode.subsystems.turret.TurretConstants.turretKi, org.firstinspires.ftc.teamcode.subsystems.turret.TurretConstants.turretKd, org.firstinspires.ftc.teamcode.subsystems.turret.TurretConstants.turretKf, org.firstinspires.ftc.teamcode.subsystems.turret.TurretConstants.turretIzone);


    public static void init(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "turret");
        resetEncoder();
    }

    public static void TurretPos(TurretState state) {
        switch (state) {
            case DEFULT:
                setTurretState(0);
                break;
            case SIDE:
                setTurretState(1);
                break;
            case BACKWARD:
                setTurretState(2);
                break;
        }
    }


//    public static void operate(Gamepad gamepad) {
//
//
//        double gamepadChange = -gamepad.right_stick_x;
//
//        if (motor.getCurrentPosition() < 350 && gamepad.right_stick_x > 0) {
//
//            gamepadChange = 0;
//        }
//
//
//        if (motor.getCurrentPosition() > 2750 && gamepad.right_stick_x < 0) {
//
//            gamepadChange = 0;
//
//        }
//        motor.setPower(gamepadChange);


//    }

    public static double getPosition() {
        return motor.getCurrentPosition();
    }


    public static boolean isFinishedMoving = false;

    public static void setTurretState(int turretPos) {

        int wanted;
        switch (turretPos) {
            case 0:
                wanted = TurretConstants.turretDefult;
                break;
            case 1:
                wanted = TurretConstants.turretSide;
                break;
            case 2:
                wanted = TurretConstants.turretBackward;
                break;
            default:
                wanted = TurretConstants.turretDefult;
        }
        setPosition(wanted);
    }

    private static void setPosition(double wantedPosition) {

        turretPID.setWanted(wantedPosition);
        motor.setPower(turretPID.update(getPosition()));

        isFinishedMoving = wantedPosition != 0 ? Math.abs(motor.getCurrentPosition()) > Math.abs(wantedPosition) - 20 : Math.abs(motor.getCurrentPosition()) < Math.abs(wantedPosition) + 20;
    }

    public static void setPosAuto(int turretPos){
        while (!isFinishedMoving()){setTurretState(turretPos);}
        isFinishedMoving = false;
        breakMotor();
    }


    public static boolean isFinishedMoving() {
        return isFinishedMoving;
    }

    public static void resetEncoder() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public static void breakMotor(){
        motor.setPower(0);
    }
}
