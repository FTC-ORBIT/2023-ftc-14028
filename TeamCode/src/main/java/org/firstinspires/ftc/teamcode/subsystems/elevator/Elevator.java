package org.firstinspires.ftc.teamcode.subsystems.elevator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PID;

public class Elevator {

    static DcMotor motor;
    private static final PID elevatorPID = new PID(ElavatorConstants.elevatorKp, ElavatorConstants.elevatorKi, ElavatorConstants.elevatorKd, ElavatorConstants.elevatorKf, ElavatorConstants.elevatorIZone);

    public static void init(HardwareMap hardwareMap){
        motor = hardwareMap.get(DcMotor.class,"elevator");
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


    private static boolean isFinishedFloor = false;

    public static void setFloor(int floor){
//      getting wanted by floor
        int wanted;
        switch (floor) {
            case 0:
                wanted = ElavatorConstants.basePos;
                break;
            case 1:
                wanted = ElavatorConstants.level1Pos;
                break;
            case 2:
                wanted = ElavatorConstants.level2Pos;
                break;
            case 3:
                wanted = ElavatorConstants.level3Pos;
            case 4:
                wanted = ElavatorConstants.level4Pos;
                break;
            default:
                wanted = ElavatorConstants.basePos;
                break;
        }
        elevatorPID.setWanted(wanted);
        motor.setPower(elevatorPID.update(motor.getCurrentPosition()));

        isFinishedFloor = Math.abs(motor.getCurrentPosition()) > Math.abs(wanted);
    }

    public static boolean isIsFinishedElevating(){
        return isFinishedFloor;
    }

    public static double getMotorPos(){
        return motor.getCurrentPosition();
    }

}
