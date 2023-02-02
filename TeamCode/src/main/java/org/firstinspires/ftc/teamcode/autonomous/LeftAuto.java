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

        AprilTagDetector.runAprilTagDetection(this);

        waitForStart();

        Pinch.closePinch();
//
//        DriveTrain.moveXY(150, 50,this);
//        sleep(100);
//
//        DriveTrain.moveXY(0,2300,this);
//        sleep(100);
//
//        DriveTrain.moveXY(650,0,this);
//        sleep(100);
//
//        Elevator.setStateAut(4,this);
//        sleep(100);
//
//        Elevator.setStateAut(7,this);
//
//        DriveTrain.moveXY(0,50,this);
//        sleep(100);
//
//        Pinch.openPinch();
//        sleep(100);
//
//        DriveTrain.moveXY(0,-150,this);
//
//        Elevator.setStateAut(8,this);
//
//        Turret.setPosAuto(1);
//
//        DriveTrain.moveXY(-1850,50,this);
//
//        Pinch.closePinch();
//
//        Turret.setPosAuto(0);
//
//        Elevator.setStateAut(4,this);
//
//        DriveTrain.moveXY(1850,0,this);
//
//        Elevator.setStateAut(7,this);
//
//        Pinch.openPinch();












        //        DriveTrain.moveXY(0, 1120, this);
//        sleep(10);
//
//        DriveTrain.moveXY(740, 0, this);
//        sleep(10);
//        Elevator.setStateAut(3, this);
//        Elevator.breakMotor();
//        sleep(10);
//
//        DriveTrain.moveXY(0, 130, this);
//
//        Elevator.setStateAut(5, this);
//
//        Pinch.openPinch();
//        sleep(50);
//
//        DriveTrain.moveXY(0, -210, this);
//
//        Elevator.setStateAut(0, this);
//
//        sleep(10);
//        DriveTrain.moveXY(-600, 0, this);
//        sleep(10);
//
//        DriveTrain.moveXY(0, 1220, this);
//
//        Elevator.setStateAut(6, this);
//
//        Turret.setPosAuto(1);
//
//        Pinch.openPinch();
//
//        DriveTrain.moveXY(-1300, 0, this);
//
//        Pinch.closePinch();
//        sleep(200);
//
//        Elevator.setStateAut(7, this);
//
//        Turret.setPosAuto(2);
//
//        DriveTrain.moveXY(1870, 0, this);
//
//        DriveTrain.moveXY(0, -110, this);
//
//        Elevator.setStateAut(5, this);
//
//        Pinch.openPinch();
//
//        DriveTrain.moveXY(0, 120, this);
//
//        Turret.setPosAuto(0);
//
//        Elevator.setStateAut(0, this);
//
//        DriveTrain.moveXY(0, -130, this);
//




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