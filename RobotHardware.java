package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {

    HardwareMap hwMap;

    public DcMotor rightMotor = null;
    public DcMotor leftMotor = null;

    public DcMotor armMotor = null;

    public CRServo grabServoRight = null;
    public CRServo grabServoLeft = null;

    public GyroSensor gyroSensor = null;

    public void Init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        rightMotor = hwMap.get(DcMotor.class, "rightMotor");
        leftMotor = hwMap.get(DcMotor.class, "leftMotor");

        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armMotor = hwMap.get(DcMotor.class, "armMotor");
        armMotor.setPower(0);

        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        grabServoLeft = hwMap.get(CRServo.class,"grabServoLeft");
        grabServoLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        grabServoRight = hwMap.get(CRServo.class,"grabServoRight");
        grabServoRight.setDirection(DcMotorSimple.Direction.FORWARD);

        gyroSensor.calibrate();
    }

}
