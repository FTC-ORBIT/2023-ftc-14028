package org.firstinspires.ftc.teamcode.subsystems.elevator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PID;

public class Elevator {

    static DcMotor motor;
    PID ElevatorPID = new PID(ElevatorConstants.elevatorKp, ElevatorConstants.elevatorKi, ElevatorConstants.elevatorKd, ElevatorConstants.elevatorKf, ElevatorConstants.elevatorIZone);

    public static void init(HardwareMap hardwareMap){
        motor = hardwareMap.get(DcMotor.class,"0");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public static void operate(ElevatorState state) {
        switch (state){

            case BASE:
                setFloor(0);
                break;
            case LEVEL1:
                setFloor(1);
                break;
            case LEVEL2:
                setFloor(2);
                break;
            case LEVEL3:
                setFloor(3);
                break;
            case LEVEL4:
                setFloor(4);
                break;
        }

    }

    private static void setFloor(int floor){

    }
}
