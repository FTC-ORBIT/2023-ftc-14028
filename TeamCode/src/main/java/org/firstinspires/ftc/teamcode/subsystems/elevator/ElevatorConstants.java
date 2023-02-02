package org.firstinspires.ftc.teamcode.subsystems.elevator;

public class ElevatorConstants {

//    PID
    public static double elevatorKp = 0.001;
    public static double elevatorKi = 0;//TODO: config
    public static double elevatorKd = 0;//TODO: config
    public static double elevatorKf = 0;//TODO: config
    public static double elevatorIZone = 0;//TODO: config

//    elevator floors
    public static final int basePos = 0;
    public static final int level1Pos = 90;
    public static final int level2Pos = 1200;
    public static final int level3Pos = 2050;
    public static final int level4Pos = 2675;//TODO: config
    public static final int level2DownPos = 1100;
    public static final int level3DownPos = 1950;
    public static final int level4DownPos = 2500;//TODO: config (level 4 - 200)
    public static final int sidePickupPos = 540;
}
