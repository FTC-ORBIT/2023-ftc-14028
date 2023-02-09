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
public class Teleop extends OpMode{

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

    private static void subSystemManager(Gamepad gamepad1, Gamepad gamepad2) {

        changeTurretState(gamepad2);
        Elevator.manualEevator(gamepad1);

        state = gamepad1.a ? RobotState.INTAKE : state;
        state = gamepad1.b ? RobotState.TRAVEL1 : state;
        state = gamepad1.y ? RobotState.TRAVEL4 : state;
        state = gamepad1.x ? RobotState.TRAVEL2 : state;
        state = gamepad1.left_bumper ? RobotState.DROP : state;
        state = gamepad1.right_bumper ? RobotState.TRAVEL3 : state;
        if (gamepad1.back) {
            OrbitGyro.resetGyro();
        }


        switch (state) {
            case TRAVEL1:
                pinchState = PinchState.CLOSE;
                elevatorState = ElevatorState.LEVEL2;
                if (Elevator.getMotorPos() > 1000) {
                    turretState = TurretState.BACKWARD;
                }
                changeFloors(gamepad1);
                changeTurretState(gamepad1);
                Pinch.closePinch();

                break;
            case INTAKE:
                turretState = TurretState.DEFULT;
                pinchState = PinchState.OPEN;
                if (Turret.getPosition() < 1000) {
                    elevatorState = ElevatorState.BASE;
                    if(Elevator.getMotorPos() < 100){Pinch.openPinch();}
                }
                break;
            case DROP:
//                if (Elevator.getMotorPos() > 2800 ) {Elevator.setFloor(7);}
//                 if (Elevator.getMotorPos() >1900 &&  Elevator.getMotorPos() < 2100) {Elevator.setFloor(6);}
//                else if (Elevator.getMotorPos() >1000 && Elevator.getMotorPos() < 1300) {Elevator.setFloor(5);}
//                else {Elevator.setFloor(1);}
//                if(Elevator.isIsFinishedElevating()){Pinch.openPinch();}


              if (Elevator.getMotorPos() > 800) {
                  changeFloors(gamepad1);

                  if (Elevator.getMotorPos() - 200 > ElevatorConstants.level4Pos && Elevator.getMotorPos() > ElevatorConstants.level3Pos) {
                     Pinch.openPinch();
                  } else if (Elevator.getMotorPos() - 200 > ElevatorConstants.level3Pos && Elevator.getMotorPos() > ElevatorConstants.level2Pos) {
                      Pinch.openPinch();
                  } else if (Elevator.getMotorPos() - 200 > ElevatorConstants.level2Pos && Elevator.getMotorPos() > ElevatorConstants.level1Pos) {
                      Pinch.openPinch();
                  } else {
                      Pinch.openPinch();
                  }
              } else {Pinch.openPinch();}

                break;

            case TRAVEL2:
                pinchState = PinchState.CLOSE;
                elevatorState = ElevatorState.LEVEL3;
              if(Elevator.getMotorPos() > 1000)  {turretState = TurretState.BACKWARD;}
                changeTurretState(gamepad1);
                changeFloors(gamepad1);

                Pinch.closePinch();
                break;
            case TRAVEL3:
                pinchState = PinchState.CLOSE;
                turretState = TurretState.DEFULT;
               if(Turret.getPosition() < 1000){ elevatorState = ElevatorState.LEVEL1;}
                changeTurretState(gamepad1);
                changeFloors(gamepad1);
                Pinch.closePinch();
                break;
            case TRAVEL4:
                pinchState = PinchState.CLOSE;
                elevatorState = ElevatorState.LEVEL4;
                if(Elevator.getMotorPos() > 1000)  {turretState = TurretState.BACKWARD;}
                changeTurretState(gamepad1);
                changeFloors(gamepad1);
                Pinch.closePinch();
                break;
        }
    }

    private static void changeFloors(Gamepad gamepad){
//        elevatorState = gamepad.a ? ElevatorState.LEVEL1 : elevatorState;
//        elevatorState = gamepad.b ? ElevatorState.LEVEL2 : elevatorState;
//        elevatorState = gamepad.x ? ElevatorState.LEVEL3 : elevatorState;
//        elevatorState = gamepad.y ? ElevatorState.LEVEL4 : elevatorState;
        elevatorState = gamepad.left_bumper ? ElevatorState.LEVEL2 : elevatorState;
    }
    private static void changeTurretState(Gamepad gamepad){
        turretState = gamepad.dpad_left ? TurretState.SIDE : turretState;
        turretState = gamepad.dpad_up ? TurretState.DEFULT : turretState;


    }
}
