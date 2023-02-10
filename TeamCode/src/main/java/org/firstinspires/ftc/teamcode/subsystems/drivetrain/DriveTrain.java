package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.InstanceOpModeManager;
import org.firstinspires.ftc.teamcode.PID;
import org.firstinspires.ftc.teamcode.hardware.OrbitGyro;
import org.firstinspires.ftc.teamcode.subsystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.utils.Vector;
import org.opencv.core.TickMeter;


public class DriveTrain {

    private static ElapsedTime elapsedTime = new ElapsedTime();

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

    private static PID moveXYPID = new PID(DriveTrainConstants.moveXYKp,
            DriveTrainConstants.moveXYKi,
            DriveTrainConstants.moveXYKd,
            DriveTrainConstants.moveXYKf,
            DriveTrainConstants.moveXYIZone);


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
        resetMotors();
    }

    public static void operate(Gamepad gamepad) {
        final Vector finalVector = vectorRotation(gamepad.left_stick_x, gamepad.left_stick_y, OrbitGyro.wrapAngle360(OrbitGyro.getAngle()));


        lf.setPower(-finalVector.y + finalVector.x - gamepad.left_trigger + gamepad.right_trigger);
        rf.setPower(-finalVector.y - finalVector.x + gamepad.left_trigger - gamepad.right_trigger);
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

        while (Math.abs(OrbitGyro.wrapAnglePlusMinus180(OrbitGyro.getAngle())) < Math.abs(wanted) && opMode.opModeIsActive()) {

            final double angle = OrbitGyro.wrapAnglePlusMinus180(OrbitGyro.getAngle());

            lf.setPower(turnRobotPID.update(angle));
            lb.setPower(turnRobotPID.update(angle));
            rf.setPower(-turnRobotPID.update(angle));
            rb.setPower(-turnRobotPID.update(angle));
        }
        isFinishedTurning = Math.abs(OrbitGyro.getAngle()) >= Math.abs(wanted);
    }


    public static void moveXY(double wantedX, double wantedY, LinearOpMode opMode) {
        resetMotors();

        double leftWanted = wantedY + wantedX;
        double rightWanted = wantedY - wantedX;

        double max = Math.max(Math.abs(leftWanted), Math.abs(rightWanted));
        double leftFactor = leftWanted / max;
        double rightFactor = rightWanted / max;

        if (Math.abs(leftWanted) < Math.abs(rightWanted)) {
            moveXYPID.setWanted(rightWanted);
            while ((Math.abs(rf.getCurrentPosition()) < Math.abs(rightWanted) && opMode.opModeIsActive())) {
                lf.setPower(Math.min(0.5, Math.max(0.15, moveXYPID.update(Math.abs(rf.getCurrentPosition())))) * leftFactor);
                rf.setPower(Math.min(0.5, Math.max(0.15, moveXYPID.update(Math.abs(rf.getCurrentPosition())))) * rightFactor);
                rb.setPower(Math.min(0.5, Math.max(0.15, moveXYPID.update(Math.abs(rf.getCurrentPosition())))) * leftFactor);
                lb.setPower(Math.min(0.5, Math.max(0.15, moveXYPID.update(Math.abs(rf.getCurrentPosition())))) * rightFactor);
                //Elevator.setFloor(floor);
            }

        } else {
            moveXYPID.setWanted(Math.abs(leftWanted));
            while ((Math.abs(lf.getCurrentPosition()) < Math.abs(leftWanted) && opMode.opModeIsActive())) {
                lf.setPower(Math.min(0.5, Math.max(0.15, moveXYPID.update(Math.abs(lf.getCurrentPosition())))) * leftFactor);
                rf.setPower(Math.min(0.5, Math.max(0.15, moveXYPID.update(Math.abs(lf.getCurrentPosition())))) * rightFactor);
                rb.setPower(Math.min(0.5, Math.max(0.15, moveXYPID.update(Math.abs(lf.getCurrentPosition())))) * leftFactor);
                lb.setPower(Math.min(0.5, Math.max(0.15, moveXYPID.update(Math.abs(lf.getCurrentPosition())))) * rightFactor);
                opMode.telemetry.addData("current wheels", lf.getCurrentPosition());
                opMode.telemetry.update();
                //Elevator.setFloor(floor);

                opMode.telemetry.addData("current elevator state", Elevator.isIsFinishedElevating());
                opMode.telemetry.update();

            }
        }

        breakMotors(opMode);
    }


    private static void resetMotors() {
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private static void breakMotors(LinearOpMode opMode) {
        double startTime = elapsedTime.time();

        while (elapsedTime.time() - 0.2 < startTime && opMode.opModeIsActive()) {
            lf.setPower(0);
            rf.setPower(0);
            rb.setPower(0);
            lb.setPower(0);
        }
    }
}

