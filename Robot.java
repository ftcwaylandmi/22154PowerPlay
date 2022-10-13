package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    RobotHardware robotHardware = new RobotHardware();

    public void InitHardware(HardwareMap ahw){
        robotHardware.Init(ahw);
    }

    public void LeftDriveMotor(double power){
        robotHardware.leftMotor.setPower(power);
    }

    public void RightDriveMotor(double power){
        robotHardware.rightMotor.setPower(power);
    }

    public void Drive(double lP, double rP){
        robotHardware.leftMotor.setPower(lP);
        robotHardware.rightMotor.setPower(rP);
    }

    public void ArmMotor(double power){
        robotHardware.armMotor.setPower(power);
    }

    public void servoIn(){
        robotHardware.grabServoLeft.setPower(1);
        robotHardware.grabServoRight.setPower(-1);

    }
    public void servoOut(){
        robotHardware.grabServoLeft.setPower(-1);
        robotHardware.grabServoRight.setPower(1);
    }

    public void stopServo(){
        robotHardware.grabServoLeft.setPower(0);
        robotHardware.grabServoRight.setPower(0);
    }
}
