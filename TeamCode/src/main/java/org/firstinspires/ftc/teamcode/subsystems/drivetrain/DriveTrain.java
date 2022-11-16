package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PID;
import org.firstinspires.ftc.teamcode.subsystems.elevator.ElevatorConstants;

public class DriveTrain {

    private static DcMotor lf;
    private static DcMotor rf;
    private static DcMotor lb;
    private static DcMotor rb;

    private static double LfPower;
    private static double RfPower;
    private static double LbPower;
    private static double RbPower;

    static DcMotor motor;

    public static void init(HardwareMap hardwareMap) {


            motor = hardwareMap.get(DcMotor.class, "0");
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            int x;
    }

    public static void operate() {

    }


}
