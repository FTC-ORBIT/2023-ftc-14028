package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.aprilTagDetector.AprilTagDetector;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.subsystems.pinch.Pinch;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchState;
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret;
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretConstants;
import org.openftc.apriltag.AprilTagDetection;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RightRed")
public class RightRed extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {
        DriveTrain.init(this.hardwareMap);
        Elevator.init(this.hardwareMap);
        Pinch.init(this.hardwareMap);
        Turret.init(this.hardwareMap);

       // AprilTagDetector.runAprilTagDetection(this);

        waitForStart();

        Pinch.closePinch();

        DriveTrain.moveXY(0,50);


        DriveTrain.moveXY(130,0);

        DriveTrain.moveXY(0, 1250);
        sleep(10);

        DriveTrain.moveXY(-650 , 0);
        sleep(10);



        Elevator.setStateAut(3);
        Elevator.breakMotor();
        sleep(10);

         DriveTrain.moveXY(0,120);

         Elevator.setStateAut(2);

        Pinch.openPinch();
        sleep(100);

        DriveTrain.moveXY(0,-210);

        Elevator.setStateAut(0);

sleep(10);
        DriveTrain.moveXY(600, 0);


    }
}