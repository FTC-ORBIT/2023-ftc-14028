package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.aprilTagDetector.AprilTagDetector;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.pinch.Pinch;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchState;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "autonomous")
public class RightRed extends LinearOpMode {
    private static int actionNum = 1;

    public static void rightRedInit(LinearOpMode opMode) {
        DriveTrain.init(opMode.hardwareMap);



        while (opMode.opModeInInit()) {
            AprilTagDetector.runAprilTagDetection(opMode);

            Pinch.init(opMode.hardwareMap);
        }
    }

    public static void autonomousLoop(LinearOpMode opMode) {
        while (opMode.opModeIsActive()){
            switch (actionNum){
                case 1:
                    DriveTrain.turnRobot(90);
                    Elevator.setFloor(2);
                    actionNum = Elevator.isIsFinishedElevating() && DriveTrain.isFinishedTurn() ? actionNum++ : actionNum;
                case 2:
            }





            switch (AprilTagDetector.wantedParkingSpot()){
                case LEFT:
                    break;
                case MIDDLE:
                    break;
                case RIGHT:
                    break;
            }
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {
        rightRedInit(this);
        waitForStart();
        autonomousLoop(this);
    }



}