package Recorders;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ATUTestRecord {

    public static ATUTestRecorder recorder;

    //ATUTestRecord Jar used here in this function
    public static void startScreenRecorder(String methodNameofTest) throws ATUTestRecorderException {
//      if(ITestResult.FAILURE==testResult.getStatus()) {
//       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime date = LocalDateTime.now();
        String formattedDateTime = date.format(formatter);
//      String  methodNameofTest=methodName.getName();
//      String methodNameofTest = new Object() {}.getClass().getEnclosingMethod().getName();
//      String methodNameofTest = Thread.currentThread().getStackTrace()[1].getMethodName();
//      String methodNameofTest = new Object(){}.getClass().getEnclosingMethod().getName();
//      StackTraceElement[] methodNameofTest = Thread.currentThread().getStackTrace(); //methodNameofTest[2].getMethodName()
        String location = System.getProperty("user.dir") + File.separator + "recordings";
        File file = new File(location);
        if (!file.exists()) {
            file.mkdir();
        }
        recorder = new ATUTestRecorder(location, methodNameofTest + "-" + formattedDateTime, false);
        recorder.start();
//        }
    }

    //ATUTestRecord Jar used here in this function
    public static void stopScreenRecorder() throws ATUTestRecorderException {
        recorder.stop();
    }
}
