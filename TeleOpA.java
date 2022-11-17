package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp", group = "22154")
public class TeleOpA extends OpMode{

    Robot robot = new Robot();

    @Override
    public void init(){robot.InitHardware(hardwareMap);}
    @Override
    public void loop(){

//        robot.LeftDriveMotor(-gamepad1.left_stick_y);
//        robot.RightDriveMotor(-gamepad1.right_stick_y);

//        robot.ArmMotor(-gamepad2.left_stick_y);

        if(gamepad2.right_bumper){
            robot.servoIn();
        }else if(gamepad2.left_bumper){
            robot.servoOut();
        }else {
            robot.stopServo();
        }

//        if(gamepad2.a){
//            robot.EleMotorTicks(0);
//        }else if(gamepad2.x){
//            robot.EleMotorTicks(1);
//        }else if(gamepad2.b){
//            robot.EleMotorTicks(2);
//        }else if(gamepad2.y){
//            robot.EleMotorTicks(3);
//        }else{
////            if (robot.robotHardware.armMotor.isBusy()){
////
////            }else{
                robot.ArmMotorStickWithLimits(-gamepad2.left_stick_y);
//            }
//        }

        robot.FullDrive(-gamepad1.left_stick_y, -gamepad1.right_stick_x);

        telemetry.addData("RMotorTicks", robot.GetRightMotor());
        telemetry.addData("LMotorTicks", robot.GetLeftMotor());
        telemetry.addData("ArmTicks", robot.robotHardware.armMotor.getCurrentPosition());
//        telemetry.addData("Heading", robot.GetGyroHeading());
        telemetry.addData("Color", robot.GetColor());

        telemetry.update();
    }
}
