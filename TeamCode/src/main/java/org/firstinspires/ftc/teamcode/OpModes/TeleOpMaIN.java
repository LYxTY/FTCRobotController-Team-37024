package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Mechanism.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanism.MecanumDriveChasis;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotors;

public class TeleOpMaIN extends OpMode {

   // Declaration zone
    MecanumDriveChasis drive = new MecanumDriveChasis();
    double forward, strafe, rotate;
    IMU imu;
    public DcMotor intakeMotor;
    IntakeMotors im = new IntakeMotors();



    @Override
    public void init() {
        // Chasis Init
        drive.init(hardwareMap);

        // Imu init
        imu = hardwareMap.get(IMU.class, "imu");

        // Intake motor init
        im.init(hardwareMap);


    }

    @Override
    public void loop() {

        // Chasis Movement Loop
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.driveFieldRelative(forward, strafe, rotate);

        // IMU reset button
        if (gamepad1.a){
            imu.resetYaw();
        }

        // Intake motor
        if (gamepad1.b){
            intakeMotor.setPower(1.0);
        }


    }


}
