package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.aprilTagDetector.AprilTagDetector;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "autonomous")
public class Autonomous extends LinearOpMode {


    public static void autonomousInit(LinearOpMode opMode) {
        while (opMode.opModeInInit()) {
            AprilTagDetector.runAprilTagDetection(opMode);
        }
    }

    public static void autonomousLoop(LinearOpMode opMode) {
        while (opMode.opModeIsActive()){
            opMode.telemetry.addData("parking spot", AprilTagDetector.wantedParkingSpot());
            opMode.telemetry.update();
        }

    }


    @Override
    public void runOpMode() throws InterruptedException {
        autonomousInit(this);
        waitForStart();
        autonomousLoop(this);
    }
}