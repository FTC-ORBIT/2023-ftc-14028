package org.firstinspires.ftc.teamcode.utils;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Date;

public class Delay {

    private static double startTime = 0;
    private static boolean isFirstTime = true;

    public static boolean startAction(double delayTime) {
        startTime = isFirstTime ? ElapsedTime.SECOND_IN_NANO : startTime;
        isFirstTime = false;

        return false;
    }
}


