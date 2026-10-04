package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeMotors {

    public DcMotor intakeMotor;

    public void init(HardwareMap hwMap){

        intakeMotor = hwMap.get(DcMotor.class, "intake_motor");
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

}
