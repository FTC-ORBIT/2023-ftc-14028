package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.aprilTagDetector.AprilTagDetector;


@TeleOp(name = "autonomous")
public class Autonomous extends LinearOpMode {


    private static void autonomousInit(LinearOpMode opMode) {
        while (opMode.opModeInInit()) {
            opMode.telemetry.addData("parking spot", AprilTagDetector.runAprilTagDetection(opMode));
            opMode.telemetry.update();
        }
    }

    private static void run(LinearOpMode opMode) {

    }


    @Override
    public void runOpMode() throws InterruptedException {
        autonomousInit(this);
        waitForStart();
        run(this);
    }
}

