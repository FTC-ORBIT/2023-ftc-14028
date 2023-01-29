//package org.firstinspires.ftc.teamcode.autonomous;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.aprilTagDetector.AprilTagDetector;
//import org.firstinspires.ftc.teamcode.aprilTagDetector.ParkingSpot;
//import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrain;
//import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrainConstants;
//import org.firstinspires.ftc.teamcode.subsystems.elevator.Elevator;
//import org.firstinspires.ftc.teamcode.subsystems.pinch.Pinch;
//import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchState;
//import org.firstinspires.ftc.teamcode.subsystems.turret.Turret;
//import org.openftc.apriltag.AprilTagDetection;
//
//
//@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RightBlue")
//public class RightBlue extends LinearOpMode {
//    ElapsedTime runTime = new ElapsedTime();
//
//    @Override
//    public class RightBlue extends LinearOpMode {
//        private static int actionNum = 1;
//        ElapsedTime runTime = new ElapsedTime();
//
//        public static void rightBlueInit(LinearOpMode opMode) {
//            DriveTrain.init(opMode.hardwareMap);
//
//
//
//            while (opMode.opModeInInit()) {
//                AprilTagDetector.runAprilTagDetection(opMode);
//                @Override
//                    }
//                }
//
//                    while (opMode.opModeIsActive()){
//                        switch (actionNum){
//                            case 1:
//                            case 2:
//                        }
//
//
//
//
//
//                        switch (AprilTagDetector.wantedParkingSpot()){
//                            waitForStart();
//
//
//                            if (AprilTagDetector.wantedParkingSpot() != null) {
//                                switch (AprilTagDetector.wantedParkingSpot()) {
//                                    case LEFT:
//                                        break;
//                                    case MIDDLE:
//                                        break;
//                                    case RIGHT:
//                                        break;
//                                }
//                            }
//                        }
//
//
//    }
//}