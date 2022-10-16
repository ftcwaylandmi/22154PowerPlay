package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
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

    public void DriveByInches(double p, double inches) throws InterruptedException {
        DcMotor l = robotHardware.leftMotor;
        DcMotor r = robotHardware.rightMotor;

        int convertToTicks = (int) inches/2048;

        int lTicks = l.getCurrentPosition()+convertToTicks;
        int rTicks = r.getCurrentPosition()+convertToTicks;

        l.setTargetPosition(0);
        r.setTargetPosition(0);

        l.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        l.setTargetPosition(lTicks);
        r.setTargetPosition(rTicks);

        l.setPower(p);
        r.setPower(p);
        while (l.getCurrentPosition()<lTicks+100 || r.getCurrentPosition()<rTicks+100){
            wait();
        }
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
