package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotHardware {
    HardwareMap hwMap;

    public DcMotor rightMotor = null;
    public DcMotor leftMotor = null;

    public void Init(HardwareMap ahwMap){
        hwMap = ahwMap;


        rightMotor = hwMap.get(DcMotor.class, "rightMotor");
        leftMotor = hwMap.get(DcMotor.class, "leftMotor");

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}
