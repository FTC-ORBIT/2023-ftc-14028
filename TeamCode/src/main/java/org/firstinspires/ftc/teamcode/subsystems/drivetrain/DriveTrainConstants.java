package org.firstinspires.ftc.teamcode.subsystems.drivetrain;


public class DriveTrainConstants {


    public static final double TICKS_PER_REV = 537.7;

    public static final double MAX_RPM = 312;




    public static final boolean RUN_USING_ENCODER = false;



    //   PID turn robot

    public static double turnRobotKp = 0.001;//TODO: config
    public static double turnRobotKi = 0;//TODO: config
    public static double turnRobotKd = 0;//TODO: config
    public static double turnRobotKf = 0;//TODO: config
    public static double turnRobotIZone = 0;//TODO: config

//    PID move xy


    public static double moveXYKp = 0.001;//TODO: config
    public static double moveXYKi = 0;//TODO: config
    public static double moveXYKd = 0;//TODO: config
    public static double moveXYKf = 0;//TODO: config
    public static double moveXYIZone = 0;//TODO: config

}
