package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.elevator.ElevatorState;


@TeleOp(name = "TeleOp")
public class Teleop extends OpMode {
    ElevatorState elevatorState = ElevatorState.BASE;



    @Override
    public void init() {
        Elevator.init(this.hardwareMap);

    }

    @Override
    public void loop() {

        Elevator.operate(elevatorState);

    }

}
