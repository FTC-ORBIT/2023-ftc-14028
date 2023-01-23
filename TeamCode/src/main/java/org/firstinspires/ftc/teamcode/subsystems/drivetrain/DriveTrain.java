package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.internal.opmode.InstanceOpModeManager;
import org.firstinspires.ftc.teamcode.PID;
import org.firstinspires.ftc.teamcode.hardware.OrbitGyro;
import org.firstinspires.ftc.teamcode.utils.Vector;


public class DriveTrain {

    private static DcMotor lf;
    private static DcMotor rf;
    private static DcMotor lb;
    private static DcMotor rb;

    private static boolean isFinishedTurning = false;

    private static PID turnRobotPID = new PID(DriveTrainConstants.turnRobotKp,
            DriveTrainConstants.turnRobotKi,
            DriveTrainConstants.turnRobotKd,
            DriveTrainConstants.turnRobotKf,
            DriveTrainConstants.turnRobotIZone);


    public static void init(HardwareMap hardwareMap) {
        rf = hardwareMap.get(DcMotor.class, "rf");
        lf = hardwareMap.get(DcMotor.class, "lf");
        lb = hardwareMap.get(DcMotor.class, "lb");
        rb = hardwareMap.get(DcMotor.class, "rb");

        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lf.setDirection(DcMotorSimple.Direction.REVERSE);
        lb.setDirection(DcMotorSimple.Direction.REVERSE);

        OrbitGyro.init(hardwareMap);
    }

    public static void operate(Gamepad gamepad) {
        final Vector finalVector = vectorRotation(gamepad.left_stick_x, gamepad.left_stick_y, OrbitGyro.wrapAngle360(OrbitGyro.getAngle()));


        lf.setPower(finalVector.y + finalVector.x - gamepad.left_trigger + gamepad.right_trigger);
        rf.setPower(finalVector.y - finalVector.x + gamepad.left_trigger - gamepad.right_trigger);
        lb.setPower(-finalVector.y - finalVector.x - gamepad.left_trigger + gamepad.right_trigger);
        rb.setPower(-finalVector.y + finalVector.x + gamepad.left_trigger - gamepad.right_trigger);


//        lf.setPower(-gamepad.left_stick_y + gamepad.left_stick_x - gamepad.left_trigger + gamepad.right_trigger);
//        rf.setPower(-gamepad.left_stick_y - gamepad.left_stick_x + gamepad.left_trigger - gamepad.right_trigger);
//        lb.setPower(-gamepad.left_stick_y - gamepad.left_stick_x - gamepad.left_trigger + gamepad.right_trigger);
//        rb.setPower(-gamepad.left_stick_y + gamepad.left_stick_x + gamepad.left_trigger - gamepad.right_trigger);


    }


    private static Vector vectorRotation(double x, double y, double angle) {
        return new Vector(x * Math.cos(Math.toRadians(angle)) - y * Math.sin(Math.toRadians(angle)),
                x * Math.sin(Math.toRadians(angle)) + y * Math.cos(Math.toRadians(angle)));
    }

    public static void turnRobot(double wanted, LinearOpMode opMode) {
        turnRobotPID.setWanted(wanted);

        while (Math.abs(OrbitGyro.wrapAnglePlusMinus180(OrbitGyro.getAngle())) < Math.abs(wanted) && opMode.opModeIsActive()){

            final double angle = OrbitGyro.wrapAnglePlusMinus180(OrbitGyro.getAngle());

            lf.setPower(turnRobotPID.update(angle));
            lb.setPower(turnRobotPID.update(angle));
            rf.setPower(-turnRobotPID.update(angle));
            rb.setPower(-turnRobotPID.update(angle));
        }
//        isFinishedTurning = Math.abs(angle) >= Math.abs(wanted);
    }

    public static boolean isFinishedTurn() {

        return isFinishedTurning;
    }

    public static void drive(Vector drive, double maxPower) {
        final double lfPower = drive.y + drive.x;
        final double rfPower = drive.y - drive.x;
        final double lbPower = drive.y - drive.x;
        final double rbPower = drive.y + drive.x;
        final double max = Math.max(Math.abs(lfPower),
                Math.max(Math.abs(lbPower), Math.max(Math.abs(rfPower), Math.abs(rbPower))));
        if (max > 1)
            maxPower = max;
        lf.setPower(DriveTrainConstants.turnRobotIZone * (lfPower / maxPower));
        rf.setPower(DriveTrainConstants.turnRobotIZone * (rfPower / maxPower));
        lb.setPower(DriveTrainConstants.turnRobotIZone * (lbPower / maxPower));
        rb.setPower(DriveTrainConstants.turnRobotIZone * (rbPower / maxPower));
    }

    public static void moveStraight(double wanted, LinearOpMode opMode) {
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while (Math.abs(lf.getCurrentPosition())< wanted && opMode.opModeIsActive()) {
            lf.setPower(-0.2);
            rf.setPower(-0.2);
            lb.setPower(0.2);
            rb.setPower(0.2);
        }
        lf.setPower(0);
        rf.setPower(0);
        lb.setPower(0);
        rb.setPower(0);
    }

    public static void moveBack(double wanted, LinearOpMode opMode) {
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while (Math.abs(lf.getCurrentPosition())< wanted && opMode.opModeIsActive()) {
            lf.setPower(0.2);
            rf.setPower(0.2);
            lb.setPower(-0.2);
            rb.setPower(-0.2);
        }
        lf.setPower(0);
        rf.setPower(0);
        lb.setPower(0);
        rb.setPower(0);
    }

    public static void moveRight(double wanted, LinearOpMode opMode) {
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while (Math.abs(lf.getCurrentPosition())< wanted && opMode.opModeIsActive()) {
            lf.setPower(0.5);
            rf.setPower(-0.5);
            lb.setPower(-0.5);
            rb.setPower(0.5);
        }
        lf.setPower(0);
        rf.setPower(0);
        lb.setPower(0);
        rb.setPower(0);

    }

    public static void moveLeft(double wanted, LinearOpMode opMode) {
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while (Math.abs(lf.getCurrentPosition())< wanted && opMode.opModeIsActive()) {
            lf.setPower(-0.5);
            rf.setPower(0.5);
            lb.setPower(0.5);
            rb.setPower(-0.5);
        }
        lf.setPower(0);
        rf.setPower(0);
        lb.setPower(0);
        rb.setPower(0);
    }
}
