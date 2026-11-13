package org.firstinspires.ftc.teamcode.FlyWheel;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp
@Disabled
public class KsTuner extends OpMode {

    flyWheelmain flywheel = new flyWheelmain();

    public static double kS = 0.17;

    double[] increments = {0.000001, 0.00001, 0.0001, 0.001, 0.01};

    int incrementsIdx = 4;

    @Override
    public void init(){

        flywheel.init(hardwareMap);

    }


    @Override
    public void loop() {

    if(gamepad1.dpadRightWasPressed() && incrementsIdx < 4){
            incrementsIdx++;
        } else if (gamepad1.dpadLeftWasPressed() && incrementsIdx > 0) {
            incrementsIdx--;
        }

        double currentStep = increments[incrementsIdx];

        // Change Ks
        if (gamepad1.dpadUpWasPressed()){ kS += currentStep; }
        if (gamepad1.dpadDownWasPressed()){kS -= currentStep; }


        flywheel.motorPower(kS);


        telemetry.addData("Current Step", currentStep);
        telemetry.addData("kS", "%.6f", kS);
        telemetry.addData("RPM", flywheel.getRPM());
        telemetry.addData("Ticks per sec", flywheel.getTicksPerSec());


    }

}
