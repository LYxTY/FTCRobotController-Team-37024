package org.firstinspires.ftc.teamcode.FlyWheel;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
@Disabled
public class KvTuner extends OpMode {

    flyWheelmain flywheel = new flyWheelmain();

    public static double kV = 0.1;

    public double kS = 0.17;

    public double goalRPM = 1500;

    double[] increments = {0.000001, 0.00001, 0.0001, 0.001, 0.01};

    int incrementsIdx = 4;

    @Override
    public void init() {

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

        // Change Kv
        if (gamepad1.dpadUpWasPressed()){ kV += currentStep; }
        if (gamepad1.dpadDownWasPressed()){kV -= currentStep; }


        double power = (kV * goalRPM) * kS;

        flywheel.motorPower(power);


        telemetry.addData("Current Step", currentStep);
        telemetry.addData("kv", "%.6f", kV);
        telemetry.addData("RPM", flywheel.getRPM());
        telemetry.addData("Ticks per sec", flywheel.getTicksPerSec());


        if (gamepad1.a){
            goalRPM = 1500;
        }
        else if (gamepad1.b){
            goalRPM = 800;
        }

    }

}
