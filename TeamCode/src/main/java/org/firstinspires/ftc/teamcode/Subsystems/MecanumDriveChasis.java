package org.firstinspires.ftc.teamcode.Mechanism;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.Set;


public class MecanumDriveChasis {

    private DcMotor FrontLeftMotor;
    private DcMotor FrontRightMotor;
    private DcMotor BackLeftMotor;
    private DcMotor BackRightMotor;


    private IMU imu;


    public void init(HardwareMap hwMap){

        FrontLeftMotor = hwMap.get(DcMotor.class, "front_left_motor");
        FrontRightMotor = hwMap.get(DcMotor.class, "front_right_motor");
        BackLeftMotor = hwMap.get(DcMotor.class, "back_left_motor");
        BackRightMotor = hwMap.get(DcMotor.class, "back_right_motor");


        FrontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        BackLeftMotor.setDirection(DcMotor.Direction.REVERSE);


        FrontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        imu = hwMap.get(IMU.class, "imu");


        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(

                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

        imu.initialize(new IMU.Parameters(RevOrientation));

    }



    public void drive(double forward, double strafe, double rotate){

        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;



        double maxPower = 1.0;
        double maxSpeed = 1.0;

        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));



        FrontLeftMotor.setPower(maxSpeed * (frontLeftPower / maxPower));
        BackLeftMotor.setPower(maxSpeed * (backLeftPower / maxPower));
        FrontRightMotor.setPower(maxSpeed * (frontRightPower / maxPower));
        BackRightMotor.setPower(maxSpeed * (backRightPower / maxPower));
    }

    public void driveFieldRelative(double forward, double strafe, double rotate){
        double theta = Math.atan2(forward, strafe);
        double r = Math.hypot(strafe, forward);


        theta = AngleUnit.normalizeRadians(theta -
                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        double newForward = r * Math.sin(theta);
        double newStrafe = r * Math.cos(theta);

        this.drive(newForward, newStrafe, rotate);


    }
}
