package org.firstinspires.ftc.teamcode.Turret;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List

public class HuskyLensAprilTagRecognition {

    private HuskyLens huskyLens;

    public void init(HardwareMap hwMap){

        huskyLens = hwMap.get(HuskyLens.class, "huskylens")

        List<AprilTagDetection> = new AprilTagProcessor();

    }



}
