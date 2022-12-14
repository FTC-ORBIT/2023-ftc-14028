package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.hardware.OrbitGyro;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchState;


@TeleOp(name = "TeleOp")
public class Teleop extends OpMode{

    private static RobotState state = RobotState.TRAVEL;
    private static ElevatorState elevatorState = ElevatorState.BASE;
    private static PinchState pinchState = PinchState.OPEN;


    @Override
    public void init() {
        OrbitGyro.init(hardwareMap);
        DriveTrain.init(hardwareMap);
//        Elevator.init(hardwareMap);
//        Turret.init(hardwareMap);
//        Pinch.init(hardwareMap);
    }

    @Override
    public void loop() {
        subSystemManager(gamepad1, gamepad2);

        DriveTrain.operate(gamepad1);
//        Elevator.operate(elevatorState);
//        Turret.operate(gamepad2);
//        Pinch.operate(pinchState);
    }

    private static void subSystemManager(Gamepad gamepad1, Gamepad gamepad2){
        state = gamepad1.a ? RobotState.INTAKE : state;
        state = gamepad1.b ? RobotState.TRAVEL : state;
        state = gamepad1.x ? RobotState.DROP : state;
        state = gamepad1.y ? RobotState.DROP : state;





        switch (state){
            case TRAVEL:
                pinchState = PinchState.CLOSE;
                changeFloors(gamepad2);
                break;
            case INTAKE:
                elevatorState = ElevatorState.BASE;
                pinchState = PinchState.OPEN;
                break;
            case DROP:
                pinchState = PinchState.OPEN;
                break;
        }
    }

    private static void changeFloors(Gamepad gamepad){
        elevatorState = gamepad.a ? ElevatorState.LEVEL1 : elevatorState;
        elevatorState = gamepad.b ? ElevatorState.LEVEL2 : elevatorState;
        elevatorState = gamepad.x ? ElevatorState.LEVEL3 : elevatorState;
        elevatorState = gamepad.y ? ElevatorState.LEVEL4 : elevatorState;
    }

}
