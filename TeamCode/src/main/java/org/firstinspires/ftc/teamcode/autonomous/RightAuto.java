//package org.firstinspires.ftc.teamcode.autonomous;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.aprilTagDetector.AprilTagDetector;
//import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrain;
//import org.firstinspires.ftc.teamcode.subsystems.elevator.Elevator;
//import org.firstinspires.ftc.teamcode.subsystems.pinch.Pinch;
//import org.firstinspires.ftc.teamcode.subsystems.turret.Turret;
//
//
//@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Right")
//public class RightAuto extends LinearOpMode {
//
//
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        DriveTrain.init(this.hardwareMap);
//        Elevator.init(this.hardwareMap);
//        Pinch.init(this.hardwareMap);
//        Turret.init(this.hardwareMap);
//
//        AprilTagDetector.runAprilTagDetection(this);
//
//        waitForStart();
//
//        Pinch.closePinch();
//
//        DriveTrain.moveXY(0,50,this);
//
//
//        DriveTrain.moveXY(130,0, this);
//
//        DriveTrain.moveXY(0, 1100, this);
//        sleep(10);
//
//        DriveTrain.moveXY(-585 , 0, this);
//        sleep(10);
//
//
//
//        Elevator.setStateAut(3, this);
//        Elevator.breakMotor();
//        sleep(10);
//
//         DriveTrain.moveXY(0,130, this);
//
//         Elevator.setStateAut(5,this);
//
//        Pinch.openPinch();
//        sleep(100);
//
//        DriveTrain.moveXY(0,-210, this);
//
//        Elevator.setStateAut(0,this);
//
//        sleep(10);
//        DriveTrain.moveXY(600, 0, this);
//        sleep(10);
//
//        DriveTrain.moveXY(0, 100, this);
//
//        switch (AprilTagDetector.wantedParkingSpot()){
//            case RIGHT:
//                DriveTrain.moveXY(1200,0, this);
//                break;
//
//            case LEFT:
//                DriveTrain.moveXY(-1200,0, this);
//                break;
//
//            case MIDDLE:
//                break;
//        }
//
//    }
//}