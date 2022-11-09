package org.firstinspires.ftc.teamcode.subsystems.elevator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PID;

import java.util.WeakHashMap;

public class Elevator {

    static DcMotor motor;
    private static final PID elevatorPID = new PID(ElevatorConstants.elevatorKp, ElevatorConstants.elevatorKi, ElevatorConstants.elevatorKd, ElevatorConstants.elevatorKf, ElevatorConstants.elevatorIZone);

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
//      getting wanted by floor
        int wanted;
        switch (floor) {
            case 0:
                wanted = ElevatorConstants.basePos;
                break;
            case 1:
                wanted = ElevatorConstants.level1Pos;
                break;
            case 2:
                wanted = ElevatorConstants.level2Pos;
                break;
            case 3:
                wanted =ElevatorConstants.level3Pos;
            case 4:
                wanted = ElevatorConstants.level4Pos;
                break;
            default:
                wanted = ElevatorConstants.basePos;
                break;
        }
        elevatorPID.setWanted(wanted);
        motor.setPower(elevatorPID.update(motor.getCurrentPosition()));
    }
}