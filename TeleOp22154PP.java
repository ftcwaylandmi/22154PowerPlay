package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp22154PP", group = "22154")
public class TeleOp22154PP extends OpMode{

    Robot robot = new Robot();

    @Override
    public void init(){robot.InitHardware(hardwareMap);}
    @Override
    public void loop(){

        robot.LeftDriveMotor(-gamepad1.left_stick_y);
        robot.RightDriveMotor(-gamepad1.right_stick_y);

        robot.ArmMotor(-gamepad2.left_stick_y);

        if(gamepad2.right_bumper){
            robot.servoIn();
        }else if(gamepad2.left_bumper){
            robot.servoOut();
        }else {
            robot.stopServo();
        }

        telemetry.addData("RMotorTicks", robot.GetRightMotor());
        telemetry.addData("LMotorTicks", robot.GetLeftMotor());
        telemetry.update();
    }
}
