package codes;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;


import static codes.DriverProvider.getDriver;

public class AlureListener implements ITestListener , IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        IInvokedMethodListener.super.afterInvocation(method, testResult);
        if(testResult.getStatus() == ITestResult.FAILURE){
            attachDOM();
            makeScreenShot();
        }

    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        makeScreenShot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }



    @Attachment(value="Page screen", type="image/png")
    private byte[] makeScreenShot(){
        System.out.println("Its works , gods");
        return ((TakesScreenshot)getDriver("chrome")).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value="Page source DOM", type="text/html")
    private String attachDOM(){
        System.out.println("Its works , gods");
        return getDriver("chrome").getPageSource();
    }


}
