package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ScreenshotListener extends TestListenerAdapter {


    private void takeScreenshotToFile(WebDriver driver, File file) {

        try (FileOutputStream screenShotStream = new FileOutputStream(file)) {
            screenShotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Can't write to " + file.getAbsolutePath());
        }
    }

    private boolean createFile(File file) {
        boolean createdFile = false;
        if (file.exists()) createdFile = true;
        File parentDirectory = new File(file.getParent());
        if (parentDirectory.exists() || parentDirectory.mkdirs()) {
            try {
                createdFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return createdFile;
    }


    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            ITestContext context = iTestResult.getTestContext();
            WebDriver driver = (WebDriver) context.getAttribute("driver");

            // Try with reflection API
/*      Class clazz = iTestResult.getTestClass().getRealClass();
        Field field = null;
        try {
            field = clazz.getDeclaredField("driver");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (field != null) field.setAccessible(true);
        WebDriver driver1 = (WebDriver)field.get(this);*/

            String screenshotDirectory = System.setProperty("screenshotDirectory", "target/screenshots");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss");
            String screenshotAbsolutePath = System.getProperty("screenshotDirectory") + File.separator + formatter.format(System.currentTimeMillis())
                    + "_" + iTestResult.getMethod().getMethodName() + ".png";
            File screenshot = new File(screenshotAbsolutePath);
            if (createFile(screenshot)) {
                try {
                    takeScreenshotToFile(driver, screenshot);
                } catch (ClassCastException weNeedToAugmentOurDriverObject) {
                    takeScreenshotToFile(new Augmenter().augment(driver), screenshot);
                }
                System.out.println("Written screenshot to " + screenshotAbsolutePath);
            } else {
                System.err.println("Unable to create " + screenshotAbsolutePath);
            }
        } catch (
                Exception ex) {
            System.err.println("Unable to capture screenshot...");
            ex.printStackTrace();
        }


    }


}
