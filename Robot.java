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

    public void DriveByInches(double p, double otr){
        DcMotor l = robotHardware.leftMotor;
        DcMotor r = robotHardware.rightMotor;

        final int ltpr = 230;
        final int rtpr = 288;
        final double wheelCir = 4*Math.PI;

        int ticksl = (int) ((otr*ltpr)/wheelCir);
        int ticksr = (int) ((otr*rtpr)/wheelCir);

        final int lTargetTicks = robotHardware.leftMotor.getCurrentPosition()+ticksl;
        final int rTargetTicks = robotHardware.rightMotor.getCurrentPosition()+ticksr;

        l.setTargetPosition(0);
        r.setTargetPosition(0);

        l.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        l.setTargetPosition(lTargetTicks);
        r.setTargetPosition(rTargetTicks);

        l.setPower(p*.78);
        r.setPower(p);
        if(lTargetTicks > l.getCurrentPosition() && rTargetTicks > r.getCurrentPosition()) {
            while (lTargetTicks > l.getCurrentPosition() && rTargetTicks > r.getCurrentPosition()) {
                if (lTargetTicks <= l.getCurrentPosition() && rTargetTicks <= r.getCurrentPosition()) {
                    break;
                }
            }
        }
        if(lTargetTicks < l.getCurrentPosition() && rTargetTicks < r.getCurrentPosition()) {
            while (lTargetTicks < l.getCurrentPosition() && rTargetTicks < r.getCurrentPosition()) {
                if (lTargetTicks >= l.getCurrentPosition() && rTargetTicks >= r.getCurrentPosition()) {
                    break;
                }
            }
        }
    }

    public void TurnByInches(double p, double in, int dir){
        DcMotor l = robotHardware.leftMotor;
        DcMotor r = robotHardware.rightMotor;

        final int ltpr = 230;
        final int rtpr = 288;
        final double wheelCir = 4*Math.PI;

        int ticksl = (int) ((in*ltpr)/wheelCir);
        int ticksr = (int) ((in*rtpr)/wheelCir);

        int lTicks = robotHardware.leftMotor.getCurrentPosition();
        int rTicks = robotHardware.rightMotor.getCurrentPosition();

        if(dir == -1){
            lTicks = robotHardware.leftMotor.getCurrentPosition()+ticksl;
            rTicks = robotHardware.rightMotor.getCurrentPosition()-ticksr;
        }else if(dir == 1){
            lTicks = robotHardware.leftMotor.getCurrentPosition()-ticksl;
            rTicks = robotHardware.rightMotor.getCurrentPosition()+ticksr;
        }

        l.setTargetPosition(0);
        r.setTargetPosition(0);

        l.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        l.setTargetPosition(lTicks);
        r.setTargetPosition(rTicks);

        l.setPower(p*.78);
        r.setPower(p);
        if(lTicks > l.getCurrentPosition() && rTicks < r.getCurrentPosition()) {
            while (lTicks > l.getCurrentPosition() && rTicks < r.getCurrentPosition()) {
                if (lTicks <= l.getCurrentPosition() && rTicks >= r.getCurrentPosition()) {
                    break;
                }
            }
        }
        if(lTicks < l.getCurrentPosition() && rTicks > r.getCurrentPosition()) {
            while (lTicks < l.getCurrentPosition() && rTicks > r.getCurrentPosition()) {
                if (lTicks >= l.getCurrentPosition() && rTicks <= r.getCurrentPosition()) {
                    break;
                }
            }
        }
    };

    public int GetGyroHeading() { return robotHardware.gyroSensor.getHeading(); }

    public int GetGyroX() { return robotHardware.gyroSensor.rawX(); }

    public int GetGyroY() { return robotHardware.gyroSensor.rawY(); }

    public int GetGyroZ() { return robotHardware.gyroSensor.rawZ(); }

    public int GetRightMotor(){
        return robotHardware.rightMotor.getCurrentPosition();
    }

    public int GetLeftMotor(){
        return robotHardware.leftMotor.getCurrentPosition();
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
