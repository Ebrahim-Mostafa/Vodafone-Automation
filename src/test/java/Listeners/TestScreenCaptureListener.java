package Listeners;

import Recorders.ATUTestRecord;
import Utilities.Utils;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestScreenCaptureListener implements ITestListener{

    @Override
    public void onTestFailure(ITestResult testResult) {
        try {
            Utils.takeAScreenshot(testResult.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ATUTestRecord.startScreenRecorder(testResult.getName());
        } catch (ATUTestRecorderException e) {
            e.printStackTrace();
        }

        try {
            ATUTestRecord.stopScreenRecorder();
        } catch (ATUTestRecorderException e) {
            e.printStackTrace();
        }
    }
}
