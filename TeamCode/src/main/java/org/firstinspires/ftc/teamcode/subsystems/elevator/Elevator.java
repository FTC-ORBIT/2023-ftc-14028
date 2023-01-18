package org.firstinspires.ftc.teamcode.subsystems.elevator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PID;

public class Elevator {

    static DcMotor motor;
    private static final PID elevatorPID = new PID(ElevatorConstants.elevatorKp, ElevatorConstants.elevatorKi, ElevatorConstants.elevatorKd, ElevatorConstants.elevatorKf, ElevatorConstants.elevatorIZone);

    public static void init(HardwareMap hardwareMap){
        motor = hardwareMap.get(DcMotor.class,"elevator");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        resetEncoder();
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
                wanted = ElevatorConstants.basePos;
                break;
            case 1:
                wanted = ElevatorConstants.level1Pos;
                break;
            case 2:
                wanted = ElevatorConstants.level2Pos;
                break;
            case 3:
                wanted = ElevatorConstants.level3Pos;
                break;
            case 4:
                wanted = ElevatorConstants.level4Pos;
                break;
            default:
                wanted = ElevatorConstants.basePos;
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

    public static void resetEncoder(){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

}
