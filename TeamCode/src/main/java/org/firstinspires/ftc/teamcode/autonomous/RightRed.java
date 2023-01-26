package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.aprilTagDetector.AprilTagDetector;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.subsystems.pinch.Pinch;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchState;
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret;
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretConstants;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RightRed")
public class RightRed extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {
        DriveTrain.init(this.hardwareMap);
        Elevator.init(this.hardwareMap);
        Pinch.init(this.hardwareMap);
        Turret.init(this.hardwareMap);

        waitForStart();
        DriveTrain.moveXY(150, 0);
        DriveTrain.breakMotors();
        DriveTrain.moveXY(0, 1750);
        DriveTrain.breakMotors();
        DriveTrain.moveXY(-200,0 );
        DriveTrain.breakMotors();
        Elevator.setStateAut(3);
        Elevator.breakMotor();
        Turret.setTurretPos(1);

    }
}