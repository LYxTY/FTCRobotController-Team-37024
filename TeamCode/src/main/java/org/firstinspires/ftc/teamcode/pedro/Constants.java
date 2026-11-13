package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
        public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
            c.frontLeftName.set("front_left_motor");
            c.frontRightName.set("front_right_motor");
            c.backLeftName.set("back_left_motor");
            c.backRightName.set("back_right_motor");
            c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
            c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
            c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
            c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
        });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-0.27159681470375363);
        c.yPodOffset.set(-7.179388211468074);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.1636840935411992);
                Controller secondaryTranslationalForward = Controller.proportional(0.06047689884394174);
                Controller primaryTranslationalLateral = Controller.proportional(0.21050402359215098);
                Controller secondaryTranslationalLateral = Controller.proportional(0.07777561194619652);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.017000572029009346));
                c.brake.set(Controller.proportionalFeedforward(0.014450486224657944));

                c.headingFeedback.set(Controller.proportional(2.3557568467091454));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.044968626491642516, 0.004779658673027769));

                c.linearBrakeCoefficients.set(Matrix.diag(0.038150405655313024, 0.14898188396730483));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.001927423004933173, -0.001000540715495098));

                c.maxAchievableForwardVelocity.set(52.63230945022652);
                c.maxAchievableStrafeVelocity.set(50.955874607647026);
                c.naturalForwardDeceleration.set(34.98865863126472);
                c.naturalStrafeDeceleration.set(52.21048337589351);
            }
    );

    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}