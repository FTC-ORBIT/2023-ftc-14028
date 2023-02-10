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

        Pinch.closePinch();

        AprilTagDetector.runAprilTagDetection(this);
        Pinch.closePinch();

        waitForStart();

        Pinch.closePinch();

        DriveTrain.moveXY(150, 50, this);

        DriveTrain.moveXY(0, 2260, this);

        DriveTrain.moveXY(690, -30, this);

        Elevator.setStateAut(4, this);

        DriveTrain.moveXY(0, 70, this);
        sleep(10);
        Elevator.setStateAut(7, this);

        Pinch.openPinch();

        DriveTrain.moveXY(0, -90, this);

        Turret.setPosAuto(1);


        Elevator.setStateAut(8, this);


        DriveTrain.moveXY(-1900, 150, this);
        sleep(10);

        Pinch.closePinch();
sleep(100 );

Elevator.setStateAut(3, this);

        Turret.setPosAuto(2);
        Turret.breakMotor();

        Elevator.setFloor(4);

        DriveTrain.moveXY(1870, -60, this);

        Elevator.setStateAut(6, this);

        Pinch.openPinch();

        DriveTrain.moveXY(0, 60, this);

        Turret.setPosAuto(0);

        Elevator.setStateAut(0,this);

        switch (AprilTagDetector.wantedParkingSpot()) {
            case RIGHT:
                DriveTrain.moveXY(600, -150, this);
                break;

            case LEFT:
                DriveTrain.moveXY(-1850, -200, this);
                break;

            case MIDDLE:
                DriveTrain.moveXY(-600, -150, this);
                break;
        }

    }
}