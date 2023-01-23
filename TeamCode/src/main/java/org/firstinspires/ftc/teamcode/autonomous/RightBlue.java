package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.aprilTagDetector.AprilTagDetector;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrainConstants;
import org.firstinspires.ftc.teamcode.subsystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.pinch.Pinch;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchState;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RightBlue")
public class RightBlue extends LinearOpMode {
    ElapsedTime runTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        DriveTrain.init(this.hardwareMap);
        Elevator.init(this.hardwareMap);
        Pinch.init(this.hardwareMap);

        while (opModeInInit()){
            AprilTagDetector.runAprilTagDetection(this);
        }
        waitForStart();
        Pinch.closePinch();
        DriveTrain.moveStraight(100, this);
        DriveTrain.moveRight(100, this);

        DriveTrain.moveStraight(1000, this);

        DriveTrain.moveLeft(590, this);
        Elevator.setStateAut(3);
        DriveTrain.moveStraight(65, this);
         double startTime = runTime.seconds();
        while (time- startTime< 0.5){}
        Elevator.setStateAut(2);
        Pinch.openPinch();
        startTime = runTime.seconds();
        while (time- startTime< 0.5){}
        DriveTrain.moveBack(100, this);
        DriveTrain.moveRight(625,this);

        startTime = runTime.seconds();
        while (time- startTime< 0.5){}

        if (AprilTagDetector.wantedParkingSpot() != null) {
            switch (AprilTagDetector.wantedParkingSpot()) {
                case LEFT:
                    DriveTrain.moveLeft(1230, this);
                    break;
                case RIGHT:
                    DriveTrain.moveRight(1100,this);
                    break;
            }
        }
        Elevator.setStateAut(0);

    }


}