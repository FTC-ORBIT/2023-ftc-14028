package org.firstinspires.ftc.teamcode.subsystems.elevator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PID;
import org.firstinspires.ftc.teamcode.utils.Delay;

public class Elevator {

    static DcMotor motor;
    private static final PID elevatorPID = new PID(ElevatorConstants.elevatorKp, ElevatorConstants.elevatorKi, ElevatorConstants.elevatorKd, ElevatorConstants.elevatorKf, ElevatorConstants.elevatorIZone);

    public static void init(HardwareMap hardwareMap){
        motor = hardwareMap.get(DcMotor.class,"elevator");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        resetEncoder();
    }

    public static void operate(ElevatorState state) {


            switch (state) {
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
            case 5:
                wanted =  ElevatorConstants.level5Pos;
                break;
            case 6:
                wanted = ElevatorConstants.side1Pos;
                break;
            case 7:
                wanted = ElevatorConstants.level6Pos;
                break;
            default:
                wanted = ElevatorConstants.basePos;
                break;
        }
        elevatorPID.setWanted(wanted);
        motor.setPower(elevatorPID.update(motor.getCurrentPosition()));

        isFinishedFloor = Math.abs(motor.getCurrentPosition()) > Math.abs(wanted) - 30;


    }

    public static void setStateAut(int floor, LinearOpMode opMode){
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
            case 5:
                wanted = ElevatorConstants.level5Pos;
                break;
            case 6:
                wanted = ElevatorConstants.side1Pos;
                break;
            case 7:
                wanted = ElevatorConstants.level6Pos;
                break;
            default:
                wanted = ElevatorConstants.basePos;
                break;
        }
        if (floor == 0|| floor == 2 || floor == 5){
            while (Math.abs(motor.getCurrentPosition()) > Math.abs(wanted) + 10 && opMode.opModeIsActive()){
                setFloor(floor);
            }
        }else {
            while (!(Math.abs(motor.getCurrentPosition()) > Math.abs(wanted) - 10) && opMode.opModeIsActive()) {
                setFloor(floor);
            }
        }

        breakMotor();
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
public static void breakMotor(){
        motor.setPower(0);
}
public static void manualEevator(Gamepad gamepad){

//        double gamepadChange = -gamepad.right_stick_y;
//
//        if (motor.getCurrentPosition() < 100 && gamepad.right_stick_y > 0){
//            gamepadChange = 0;
//        }
//        if(motor.getCurrentPosition() > 2950 && gamepad.right_stick_y >0){
//            gamepadChange = 0;
//        }
//        motor.setPower(-gamepad.right_stick_y);

}
}
