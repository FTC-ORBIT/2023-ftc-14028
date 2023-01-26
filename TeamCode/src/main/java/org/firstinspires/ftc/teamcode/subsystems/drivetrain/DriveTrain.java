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
import org.opencv.core.TickMeter;


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


    private static double leftFactor = 0;
    private static double rightFactor = 0;

    private static double leftWanted = 0;
    private static double rightWanted = 0;




    public static void moveXY(double wantedX, double wantedY){
        leftWanted = wantedY + wantedX;
        rightWanted = wantedY - wantedX;

        leftFactor = leftWanted / Math.max(Math.abs(leftWanted), Math.abs(rightWanted));
        rightFactor = rightWanted / Math.max(Math.abs(leftWanted), Math.abs(rightWanted));

        if (Math.abs(leftWanted) < Math.abs(rightWanted)) {
            moveXYPID.setWanted(rightWanted);
            while (Math.abs(rf.getCurrentPosition()) < Math.abs(rightWanted)){
                lf.setPower(-moveXYPID.update(rf.getCurrentPosition()) * leftFactor);
                rf.setPower(-moveXYPID.update(rf.getCurrentPosition()) * rightFactor);
                rb.setPower(moveXYPID.update(rf.getCurrentPosition()) * leftFactor);
                lb.setPower(moveXYPID.update(rf.getCurrentPosition()) * rightFactor);
            }
            breakMotors();

        }
        else {
            moveXYPID.setWanted(leftWanted);
            while (Math.abs(lf.getCurrentPosition()) < Math.abs(leftWanted)){
                lf.setPower(moveXYPID.update(lf.getCurrentPosition()) * leftFactor);
                rf.setPower(moveXYPID.update(lf.getCurrentPosition()) * rightFactor);
                rb.setPower(moveXYPID.update(lf.getCurrentPosition()) * leftFactor);
                lb.setPower(moveXYPID.update(lf.getCurrentPosition()) * rightFactor);
            }
            breakMotors();
        }



    }
    public static void breakMotors(){
        lf.setPower(0);
        rf.setPower(0);
        rb.setPower(0);
        lb.setPower(0);
    }

    private static void resetMotors(){
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}
