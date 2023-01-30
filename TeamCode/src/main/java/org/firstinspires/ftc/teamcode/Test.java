package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

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


        waitForStart();


        Pinch.openPinch();

        Elevator.setStateAut(6, this);
        sleep(10);

        Turret.setPosAuto(1);

        DriveTrain.moveXY(-400, 0, this);

        Pinch.closePinch();
        sleep(500);

        Elevator.setStateAut(3,this);

        DriveTrain.moveXY(400, 0, this);
        Turret.setPosAuto(0);
        Pinch.openPinch();
        sleep(1000);
    }
}