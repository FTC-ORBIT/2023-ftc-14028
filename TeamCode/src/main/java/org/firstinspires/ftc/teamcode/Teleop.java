package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.OrbitGyro;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.elevator.ElevatorConstants;
import org.firstinspires.ftc.teamcode.subsystems.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.subsystems.pinch.Pinch;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchConstants;
import org.firstinspires.ftc.teamcode.subsystems.pinch.PinchState;
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret;
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretState;

import java.util.concurrent.Delayed;


@TeleOp(name = "TeleOp")
public class
Teleop extends OpMode{

    private static RobotState state = RobotState.INTAKE;
    private static ElevatorState elevatorState = ElevatorState.BASE;
    private static PinchState pinchState = PinchState.CLOSE;
private static TurretState turretState = TurretState.DEFULT;


    @Override
    public void init() {
        OrbitGyro.init(hardwareMap);
        DriveTrain.init(hardwareMap);
        Elevator.init(hardwareMap);
        Turret.init(hardwareMap);
        Pinch.init(hardwareMap);
    }

    @Override
    public void loop() {
        subSystemManager(gamepad1, gamepad2);

        DriveTrain.operate(gamepad1);
        Elevator.operate(elevatorState);
      Turret.TurretPos(turretState);
       Pinch.operate(pinchState);

        telemetry.addData("position", Turret.getPosition());
       telemetry.addData("position", Elevator.getMotorPos());
        telemetry.addData("elevator state", elevatorState);
        telemetry.addData("pinch pos", pinchState);
        telemetry.addData("turret state", turretState);



    }

    private static void subSystemManager(Gamepad gamepad1, Gamepad gamepad2){

        changeTurretState(gamepad2);

        state = gamepad1.a ? RobotState.INTAKE : state;
        state = gamepad1.b ? RobotState.TRAVEL1 : state;
        state = gamepad1.x ? RobotState.TRAVEL2 : state;
        state = gamepad1.y ? RobotState.DROP : state;
        state = gamepad1.right_bumper ? RobotState.TRAVEL3 : state;
        if (gamepad1.back){OrbitGyro.resetGyro();}




        switch (state){
            case TRAVEL1:
                pinchState = PinchState.CLOSE;
                elevatorState = ElevatorState.LEVEL2;
                turretState = TurretState.BACKWARD;
                changeFloors(gamepad2);
                changeTurretState(gamepad1);
                break;
            case INTAKE:
                turretState = TurretState.DEFULT;
                pinchState = PinchState.OPEN;
                elevatorState = ElevatorState.BASE;
                break;
            case DROP:
                pinchState = PinchState.OPEN;

                break;
            case TRAVEL2:
                pinchState = PinchState.CLOSE;
                elevatorState = ElevatorState.LEVEL3;
                turretState = TurretState.BACKWARD;
                changeTurretState(gamepad1);
                break;
            case TRAVEL3:
                pinchState = PinchState.CLOSE;
                elevatorState = ElevatorState.LEVEL1;
                changeTurretState(gamepad1);
                break;

        }
    }

    private static void changeFloors(Gamepad gamepad){
        elevatorState = gamepad.a ? ElevatorState.LEVEL1 : elevatorState;
        elevatorState = gamepad.b ? ElevatorState.LEVEL2 : elevatorState;
        elevatorState = gamepad.x ? ElevatorState.LEVEL3 : elevatorState;
        elevatorState = gamepad.y ? ElevatorState.LEVEL4 : elevatorState;
    }
    private static void changeTurretState(Gamepad gamepad){
        turretState = gamepad.dpad_up ? TurretState.DEFULT : turretState;
        turretState = gamepad.dpad_left ? TurretState.SIDE : turretState;
        turretState = gamepad.dpad_down ? TurretState.BACKWARD : turretState;


    }
}
