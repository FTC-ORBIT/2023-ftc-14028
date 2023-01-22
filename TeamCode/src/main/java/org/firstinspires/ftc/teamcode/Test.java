package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.pinch.Pinch;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchConstants;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchState;


@TeleOp(name = "test")
public class Test extends OpMode {


    @Override
    public void init() {

        Pinch.init(hardwareMap);


    }

    @Override
    public void loop() {


    }
}