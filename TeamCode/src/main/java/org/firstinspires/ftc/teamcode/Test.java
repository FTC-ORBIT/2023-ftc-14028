package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.OrbitGyro;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.pinch.Pinch;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchConstants;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchState;
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret;


@TeleOp(name = "test")
public class Test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DriveTrain.init(this.hardwareMap);
        Elevator.init(this.hardwareMap);
        Pinch.init(this.hardwareMap);
        Turret.init(this.hardwareMap);
        OrbitGyro.init(this.hardwareMap);
        OrbitGyro.resetGyro();
        Elevator.getMotorPos();
        waitForStart();
        while(opModeIsActive()) {
            telemetry.addData("Elevator Pos", Elevator.getMotorPos());
            telemetry.update();
        }
//    DriveTrain.moveXY(0,1000,3,this);
//    sleep(10000);
    }
}