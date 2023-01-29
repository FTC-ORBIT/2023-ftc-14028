package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.pinch.Pinch;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchConstants;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchState;
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret;


@TeleOp(name = "test")
public class Test extends OpMode {

    private static DcMotor motor;

    @Override
    public void init() {

        motor = hardwareMap.get(DcMotor.class, "test");


    }

    @Override
    public void loop() {

motor.setPower(10000);
    }
}