package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.teamcode.Mechanism.MecanumDriveChasis;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotors;
@TeleOp(name = "TeleOp Full", group = "TeleOp")
public class TeleOpMain extends LinearOpMode {

   // Declaration zone
    MecanumDriveChasis drive = new MecanumDriveChasis(); // Import Mecanum Drive
    double forward, strafe, rotate; // Mecanum Drive variables
    IMU imu; // Imu declaration
    IntakeMotors im = new IntakeMotors(); // intake

    // Intake Variables
    public DcMotorEx intakeMotor;
    boolean intakeOn = false;
    boolean lastLBState = false;

    boolean lastRBState = false;
    double intakeVelocity = 0;

    @Override
    public void runOpMode() {
        // Chasis Init
        drive.init(hardwareMap);

        // Imu init
        imu = hardwareMap.get(IMU.class, "imu");

        // Intake motor init
        im.setIntakeMotor(hardwareMap);
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake_motor");
        intakeMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        // *******************************************************


        waitForStart();


        // *******************************************************

        while (opModeIsActive()) {

            // Chasis Movement Loop
            forward = -gamepad1.left_stick_y;
            strafe = gamepad1.left_stick_x;
            rotate = gamepad1.right_stick_x;
            drive.driveFieldRelative(forward, strafe, rotate);



            // IMU reset button
            if (gamepad1.a) {
                imu.resetYaw();
            }



            // Intake motor LeftBumper(LB) ->lastLBState
            if (gamepad1.left_bumper && !lastLBState) {
                intakeOn = !intakeOn;
            }
            lastLBState = gamepad1.left_bumper;
            if (intakeOn) {
                intakeMotor.setPower(1.0);
            } else {
                intakeMotor.setPower(0);
            }

            telemetry.addData("intakeRPM", im.getRPM());
            telemetry.update();




        }
    }


}
