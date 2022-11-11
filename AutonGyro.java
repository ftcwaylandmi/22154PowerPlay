
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name="AutonGyro", group="22154")
public class AutonGyro extends LinearOpMode {

    private Robot robot = new Robot();

    private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";
    // private static final String TFOD_MODEL_FILE  = "/sdcard/FIRST/tflitemodels/CustomTeamModel.tflite";


    private static final String[] LABELS = {
            "1 Bolt",
            "2 Bulb",
            "3 Panel"
    };

    private static final String VUFORIA_KEY =
            "AasKN9z/////AAABmY6EviEEJUTRnIuCqma4bst8h6hb6/Ux1h58a6+irwaI1/8qSx5pv0GmULXArUkQ1myuil/g3k0Xaleb3tHRNUpdII1dyOzCw9GPFF2vmmjYyVWzq29TpqbmwjBrw5Nq6o7uKhgtL4MDXSch7tCkUARTOGBSxLXe+2zsnXB8UTQkXo0GEDHuR2ere1MRC8N66U0UwNwO7obrk2kBfwFTCzPuiGU7r8lECfO3UWYVnAcDHJFhJL6hvxgXPEbZo3vgnxwjvttLU6iMTQYXwBzttw0sw3gPglOLxjs8mhxkjsF8RwGnvm4v/7v9vHFRqh45wpBaV83V6NFrbFAk+CwaSSIljTfMZ7uAfjQxZhRW1RXd";

    VuforiaLocalizer vuforia;

    TFObjectDetector tfod;

    // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
    // first.

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        // Use loadModelFromAsset() if the TF Model is built in as an asset by Android Studio
        // Use loadModelFromFile() if you have downloaded a custom team model to the Robot Controller's FLASH.
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        // tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
    }

    private String _getLabel(){
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can increase the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(1.0, 16.0/9.0);
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Objects Detected", updatedRecognitions.size());

                        // step through the list of recognitions and display image position/size information for each one
                        // Note: "Image number" refers to the randomized image orientation/number
                        for (Recognition recognition : updatedRecognitions) {
                            double col = (recognition.getLeft() + recognition.getRight()) / 2 ;
                            double row = (recognition.getTop()  + recognition.getBottom()) / 2 ;
                            double width  = Math.abs(recognition.getRight() - recognition.getLeft()) ;
                            double height = Math.abs(recognition.getTop()  - recognition.getBottom()) ;

                            return recognition.getLabel();
                        }
                        telemetry.update();
                    }
                }
            }
        }
        return null;
    }

    private int _convertLabelToInt(String label){
        switch (label){

            case "1 Bolt":
                return 1;
            case "2 Bulb":
                return 2;
            case "3 Panel":
                return 3;
        }
        return 0;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        robot.InitHardware(hardwareMap);
        initVuforia();
        initTfod();
        waitForStart();

        if(opModeIsActive()){

            robot.TurnByGyro(.5, 90,'r');

        }
    }
}
