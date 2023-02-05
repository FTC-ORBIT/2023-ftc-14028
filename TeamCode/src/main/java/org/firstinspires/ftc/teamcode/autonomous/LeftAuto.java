package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.aprilTagDetector.AprilTagDetector;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.pinch.Pinch;
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Left")
public class LeftAuto extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        DriveTrain.init(this.hardwareMap);
        Elevator.init(this.hardwareMap);
        Pinch.init(this.hardwareMap);
        Turret.init(this.hardwareMap);
        //AprilTagDetector.runAprilTagDetection(this);

        waitForStart();

        Pinch.closePinch();

        DriveTrain.moveXY(150, 50, 0, this);
        sleep(100);
        DriveTrain.moveXY(0, 2230, 0, this);
        sleep(100);

        DriveTrain.moveXY(650, 0, 0, this);
        sleep(100);
        Elevator.setStateAut(2,this);

        Elevator.setStateAut(4, this);
        sleep(100);

        DriveTrain.moveXY(0, 20, 4, this);
        sleep(100);

        Elevator.setStateAut(7, this);
        sleep(100);

        Pinch.openPinch();
        sleep(100);

        DriveTrain.moveXY(0, -150, 8, this);

        Elevator.setStateAut(8, this);
        sleep(100);

        Turret.setPosAuto(1);
        sleep(100);

        DriveTrain.moveXY(-1850, 0, 8, this);
        sleep(100);

        Pinch.closePinch();
        sleep(100);

        Elevator.setStateAut(3, this);

        Turret.setTurretState(0);
       Turret.breakMotor();
        sleep(100);

        DriveTrain.moveXY(1850, 0, 4, this);
        sleep(100);


        Elevator.setStateAut(4,this);

        Elevator.setStateAut(7, this);

        Pinch.openPinch();

        DriveTrain.moveXY(0,-70,0,this);

//        switch (AprilTagDetector.wantedParkingSpot()) {
//            case RIGHT:
//                DriveTrain.moveXY(600, 0, this);
//                break;
//
//            case LEFT:
//                DriveTrain.moveXY(-1750, 0, this);
//                break;
//
//            case MIDDLE:
//                DriveTrain.moveXY(-400, 0, this);
//                break;
//        }

    }
}