package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutonTest", group="22154")
public class AutonTest extends LinearOpMode {

    private Robot robot = new Robot();

    @Override
    public void runOpMode(){
        robot.Drive(1,1);
        sleep(1000);
        robot.Drive(0,0);
        
    }
}
