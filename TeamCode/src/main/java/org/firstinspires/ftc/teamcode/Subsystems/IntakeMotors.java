package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeMotors {

    public DcMotorEx intakeMotor;

    public void setIntakeMotor(HardwareMap hwMap){

            intakeMotor = hwMap.get(DcMotorEx.class, "intake_motor");
            intakeMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            intakeMotor.setDirection(DcMotorEx.Direction.REVERSE);

    }

    public double getRPM(){
        double tps = intakeMotor.getVelocity();
        double rpm = (tps / 383.6) * 60.0;
        return rpm;
    }

}
