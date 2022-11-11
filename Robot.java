package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    RobotHardware robotHardware = new RobotHardware();

    int eleHomeTicks = 0;

    int eleAutonHover = 800;

    int eleLowTicks = 1340;
    int eleMidTicks = 2214;
    int eleHighTicks = 3150;

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

    public void EleMotorTicks(int pos){
        robotHardware.armMotor.setTargetPosition(0);
        robotHardware.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int targetTicks = 0;
        switch (pos){
            case 0:
                robotHardware.armMotor.setTargetPosition(eleHomeTicks);
                targetTicks = eleHomeTicks;
                break;
            case 1:
                robotHardware.armMotor.setTargetPosition(eleLowTicks);
                targetTicks = eleLowTicks;
                break;
            case 2:
                robotHardware.armMotor.setTargetPosition(eleMidTicks);
                targetTicks = eleMidTicks;
                break;
            case 3:
                robotHardware.armMotor.setTargetPosition(eleHighTicks);
                targetTicks = eleHighTicks;
                break;
        }
        robotHardware.armMotor.setPower(.5);
    }
    public void EleMotorOverride(double p) {
        robotHardware.armMotor.setPower(p);
    }

    public int GetColor(){
        if(robotHardware.colorSensor.red() > robotHardware.colorSensor.blue() && robotHardware.colorSensor.red() > robotHardware.colorSensor.green()){
            return 1;
        }
        if(robotHardware.colorSensor.green() > robotHardware.colorSensor.blue() && robotHardware.colorSensor.green() > robotHardware.colorSensor.red()){
            return 2;
        }
        if(robotHardware.colorSensor.blue() > robotHardware.colorSensor.red() && robotHardware.colorSensor.blue() > robotHardware.colorSensor.green()){
            return 3;
        }
        return 0;
    }

    public void DriveByInches(double p, double otr){
        DcMotor l = robotHardware.leftMotor;
        DcMotor r = robotHardware.rightMotor;

        final int ltpr = 288;
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

        l.setPower(p);
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

    public void TurnByInches(double p, int degree, char dir){
        DcMotor l = robotHardware.leftMotor;
        DcMotor r = robotHardware.rightMotor;

        final int tpr = 540;
        final double wheelCir = 3.75*Math.PI;
        final double rotationalCir = 11.5*Math.PI;
        final double ticksPerInch = tpr/wheelCir;
        final double a135 = 3.75;
        final int a90 = 10;
        final int a45 = 20;

        int ticks = (int) ((tpr/wheelCir)*(rotationalCir/a90));

        switch (degree){
            case 135:
                ticks = (int) (ticksPerInch*(rotationalCir/a135));
                break;
            case 90:
                ticks = (int) (ticksPerInch*(rotationalCir/a90));
                break;
            case 45:
                ticks = (int) (ticksPerInch*(rotationalCir/a45));
                break;
        }

        int lTicks = robotHardware.leftMotor.getCurrentPosition();
        int rTicks = robotHardware.rightMotor.getCurrentPosition();

        if(dir == 'r'){
            lTicks = robotHardware.leftMotor.getCurrentPosition()+ticks;
            rTicks = robotHardware.rightMotor.getCurrentPosition()-ticks;
        }else{
            lTicks = robotHardware.leftMotor.getCurrentPosition()-ticks;
            rTicks = robotHardware.rightMotor.getCurrentPosition()+ticks;
        }

        l.setTargetPosition(0);
        r.setTargetPosition(0);

        l.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        l.setTargetPosition(lTicks);
        r.setTargetPosition(rTicks);

        l.setPower(p);
        r.setPower(p);
        if(lTicks > l.getCurrentPosition() && rTicks < r.getCurrentPosition()) {
            while (lTicks > l.getCurrentPosition() && rTicks < r.getCurrentPosition()) {
                if (lTicks <= l.getCurrentPosition() && rTicks >= r.getCurrentPosition()) {
                    l.setTargetPosition(l.getCurrentPosition());
                    r.setTargetPosition(r.getCurrentPosition());
                    l.setPower(.1);
                    r.setPower(.1);
                    break;
                }
            }
        }
        if(lTicks < l.getCurrentPosition() && rTicks > r.getCurrentPosition()) {
            while (lTicks < l.getCurrentPosition() && rTicks > r.getCurrentPosition()) {
                if (lTicks >= l.getCurrentPosition() && rTicks <= r.getCurrentPosition()) {
                    l.setTargetPosition(l.getCurrentPosition());
                    r.setTargetPosition(r.getCurrentPosition());
                    l.setPower(.1);
                    r.setPower(.1);
                    break;
                }
            }
        }
    };

    public void ArmMotorStickWithLimits(double pStick){

        int leftLimit = 3400;
        int rightLimit = 200;

        DcMotor armMotor = robotHardware.armMotor;

        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armMotor.setPower(pStick);

        while (armMotor.getCurrentPosition() >= rightLimit){
            if(pStick < 0){
                armMotor.setPower(pStick/2);
                break;
            }else{
                armMotor.setPower(0);
                break;
            }
        }
        while (armMotor.getCurrentPosition() <= leftLimit){
            if(pStick > 0){
                armMotor.setPower(pStick/2);
                break;
            }else {
                armMotor.setPower(0);
                break;
            }
        }
    };

    public void FullDrive(double yStick, double xStick){
        if(xStick > 0){
            robotHardware.leftMotor.setPower(yStick/2-(xStick/2));
            robotHardware.rightMotor.setPower(yStick/2+(xStick/2));
        }else if(xStick < 0){
            robotHardware.leftMotor.setPower(yStick/2-(xStick/2));
            robotHardware.rightMotor.setPower(yStick/2+(xStick/2));
        }else if(xStick == 0) {
            robotHardware.leftMotor.setPower(yStick);
            robotHardware.rightMotor.setPower(yStick);
        }else if(yStick == 0){
            if (xStick > 0){
                robotHardware.leftMotor.setPower(xStick);
                robotHardware.rightMotor.setPower(-(xStick));
            }else if (xStick < 0){
                robotHardware.leftMotor.setPower(xStick);
                robotHardware.rightMotor.setPower(-(xStick));
            }
        }
    }

    public void EleMotorTicksAuton(int pos){
        int targetTicks = 0;
        switch (pos){
            case 0:
                robotHardware.armMotor.setTargetPosition(eleHomeTicks);
                targetTicks = eleHomeTicks;
                break;
            case 1:
                robotHardware.armMotor.setTargetPosition(eleAutonHover);
                targetTicks = eleAutonHover;
                break;
            case 2:
                robotHardware.armMotor.setTargetPosition(eleLowTicks);
                targetTicks = eleLowTicks;
                break;
            case 3:
                robotHardware.armMotor.setTargetPosition(eleMidTicks);
                targetTicks = eleMidTicks;
                break;
            case 4:
                robotHardware.armMotor.setTargetPosition(eleHighTicks);
                targetTicks = eleHighTicks;
                break;

        }
        robotHardware.armMotor.setPower(1);
        if(targetTicks > robotHardware.armMotor.getCurrentPosition()) {
            while (targetTicks > robotHardware.armMotor.getCurrentPosition()) {
                if (targetTicks <= robotHardware.armMotor.getCurrentPosition()) {
                    break;
                }
            }
        }
        if(targetTicks < robotHardware.armMotor.getCurrentPosition()) {
            while (targetTicks < robotHardware.armMotor.getCurrentPosition()) {
                if (targetTicks >= robotHardware.armMotor.getCurrentPosition()) {
                    break;
                }
            }
        }
    }

//    public void TurnByInches(double p, double in, int dir){
//        DcMotor l = robotHardware.leftMotor;
//        DcMotor r = robotHardware.rightMotor;
//
//        final int ltpr = 230;
//        final int rtpr = 288;
//        final double wheelCir = 4*Math.PI;
//
//        int ticksl = (int) ((in*ltpr)/wheelCir);
//        int ticksr = (int) ((in*rtpr)/wheelCir);
//
//        int lTicks = robotHardware.leftMotor.getCurrentPosition();
//        int rTicks = robotHardware.rightMotor.getCurrentPosition();
//
//        if(dir == -1){
//            lTicks = robotHardware.leftMotor.getCurrentPosition()+ticksl;
//            rTicks = robotHardware.rightMotor.getCurrentPosition()-ticksr;
//        }else if(dir == 1){
//            lTicks = robotHardware.leftMotor.getCurrentPosition()-ticksl;
//            rTicks = robotHardware.rightMotor.getCurrentPosition()+ticksr;
//        }
//
//        l.setTargetPosition(0);
//        r.setTargetPosition(0);
//
//        l.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        r.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        l.setTargetPosition(lTicks);
//        r.setTargetPosition(rTicks);
//
//        l.setPower(p*.78);
//        r.setPower(p);
//        if(lTicks > l.getCurrentPosition() && rTicks < r.getCurrentPosition()) {
//            while (lTicks > l.getCurrentPosition() && rTicks < r.getCurrentPosition()) {
//                if (lTicks <= l.getCurrentPosition() && rTicks >= r.getCurrentPosition()) {
//                    break;
//                }
//            }
//        }
//        if(lTicks < l.getCurrentPosition() && rTicks > r.getCurrentPosition()) {
//            while (lTicks < l.getCurrentPosition() && rTicks > r.getCurrentPosition()) {
//                if (lTicks >= l.getCurrentPosition() && rTicks <= r.getCurrentPosition()) {
//                    break;
//                }
//            }
//        }
//    };

    public double GetGyroHeading(){
        return robotHardware.imu.getAngularOrientation().firstAngle;
    }

    public void TurnByGyro(double p, double angle, char dir){
        DcMotor l = robotHardware.leftMotor;
        DcMotor r = robotHardware.rightMotor;

        double angleFixed = 0;

        final int ltpr = 230;
        final int rtpr = 288;
        final double wheelCir = 4*Math.PI;

        int ticksl = (int) ((1*ltpr)/wheelCir);
        int ticksr = (int) ((1*rtpr)/wheelCir);

        int lTicks = robotHardware.leftMotor.getCurrentPosition();
        int rTicks = robotHardware.rightMotor.getCurrentPosition();

        if(dir == 'r'){
            lTicks = robotHardware.leftMotor.getCurrentPosition()+ticksl;
            rTicks = robotHardware.rightMotor.getCurrentPosition()-ticksr;
//            angleFixed = robotHardware.imu.getAngularOrientation().firstAngle + angle;
        }else{
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
        if(GetGyroHeading() > angle) {
            while (GetGyroHeading() > angle) {
                if (GetGyroHeading() <= angle) {
                    break;
                }
                if (dir == -1){
                    l.setTargetPosition(l.getCurrentPosition()+ticksl);
                    r.setTargetPosition(r.getCurrentPosition()-ticksr);
                }else if(dir == 1){
                    l.setTargetPosition(l.getCurrentPosition()-ticksl);
                    r.setTargetPosition(r.getCurrentPosition()+ticksr);
                }
            }
        }
        if(GetGyroHeading() < angle) {
            while (GetGyroHeading() < angle) {
                if (GetGyroHeading() >= angle) {
                    break;
                }
                if (dir == -1){
                    l.setTargetPosition(l.getCurrentPosition()+ticksl);
                    r.setTargetPosition(r.getCurrentPosition()-ticksr);
                }else if(dir == 1){
                    l.setTargetPosition(l.getCurrentPosition()-ticksl);
                    r.setTargetPosition(r.getCurrentPosition()+ticksr);
                }
            }
        }
    };

    public int GetRightMotor(){
        return robotHardware.rightMotor.getCurrentPosition();
    }

    public int GetLeftMotor(){
        return robotHardware.leftMotor.getCurrentPosition();
    }

    public void ArmMotor(double power){
//        robotHardware.armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
