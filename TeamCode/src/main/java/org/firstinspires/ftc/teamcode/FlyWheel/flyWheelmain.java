package org.firstinspires.ftc.teamcode.FlyWheel;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class flyWheelmain {

    private DcMotorEx m1, m2;

    private double encoderCPM = 28; // Must Modify this using our motor encoderCPM (on the motor)

    private double kV, kS, kP;

    private double gearRatio = 1; // Modify this based on what gear ratio do we have on motors

    public void init(HardwareMap hwMap){

        m1 = hwMap.get(DcMotorEx.class, "motor1_shooter");
        m2 = hwMap.get(DcMotorEx.class, "motor2_shooter" );

        m1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        m2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        m2.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void motorPower(double power){

        m1.setPower(power);
        m2.setPower(power);

    }


    public Double getTicksPerSec(){
        return m1.getVelocity();
    }


    public double getRPM(){
        return ((getTicksPerSec() / encoderCPM) * 60) / gearRatio;
    }

}
