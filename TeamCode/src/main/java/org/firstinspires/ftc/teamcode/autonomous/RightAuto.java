package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.aprilTagDetector.AprilTagDetector;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.pinch.Pinch;
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Right")
public class RightAuto extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {
        DriveTrain.init(this.hardwareMap);
        Elevator.init(this.hardwareMap);
        Pinch.init(this.hardwareMap);
        Turret.init(this.hardwareMap);


        Pinch.closePinch();
        AprilTagDetector.runAprilTagDetection(this);


        waitForStart();

                Pinch.closePinch();

                Elevator.setStateAut(1,this);

                DriveTrain.moveXY(150, 50,  this);
                sleep(100);

                DriveTrain.moveXY(0, 2270,  this);
                sleep(100);

                DriveTrain.moveXY(-670, 0,  this);
                sleep(100);

                Elevator.setStateAut(4,this);

                DriveTrain.moveXY(0,80,this);
                sleep(100);

                Elevator.setStateAut(7,this);

                Pinch.openPinch();
                sleep(100);

                DriveTrain.moveXY(0, -150,  this);
                sleep(100);
                Elevator.setStateAut(0, this);
                sleep(100);



        switch (AprilTagDetector.wantedParkingSpot()){
            case RIGHT:
                DriveTrain.moveXY(1900,-60, this);
                break;

            case LEFT:
                DriveTrain.moveXY(-700,-70, this);
                break;

            case MIDDLE:
                DriveTrain.moveXY(700,-70,this);
                break;
        }

    }
}