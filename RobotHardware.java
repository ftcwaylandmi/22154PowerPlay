package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {

    HardwareMap hwMap;

    public DcMotor rightMotor = null;
    public DcMotor leftMotor = null;

    public DcMotor armMotor = null;

    public CRServo grabServoRight = null;
    public CRServo grabServoLeft = null;

    public BNO055IMU imu = null;

    public ColorSensor colorSensor = null;
    private ColorSensor colorSensor1;

    public void Init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        imu = hwMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);

        colorSensor = hwMap.get(ColorSensor.class, "colorSensor");

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

        armMotor.setTargetPosition(0);

        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        grabServoLeft = hwMap.get(CRServo.class,"grabServoLeft");
        grabServoLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        grabServoRight = hwMap.get(CRServo.class,"grabServoRight");
        grabServoRight.setDirection(DcMotorSimple.Direction.FORWARD);

    }

}
