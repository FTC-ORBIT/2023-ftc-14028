package org.firstinspires.ftc.teamcode.subsystems.elevator;

import com.acmerobotics.dashboard.config.Config;

@Config
public class ElevatorConstants {

//    PID
    public static double elevatorKp = 0.001;
    public static double elevatorKi = 0;//TODO: config
    public static double elevatorKd = 0;//TODO: config
    public static double elevatorKf = 0;//TODO: config
    public static double elevatorIZone = 0;//TODO: config

//    elevator floors
    public static final int basePos = 0;
    public static final int level1Pos = 132;
    public static final int level2Pos = 1743;
    public static final int level3Pos = 2931;
    public static final int level4Pos = 0;//TODO: config



}
