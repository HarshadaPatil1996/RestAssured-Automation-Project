package api.utilities;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReporteManager implements ITestListener
{  
   public ExtentSparkReporter sparkreporter;//this is for the look and feel /UI of the report
   public ExtentReports extent;// this will get us all the sysystem ingo ,name of projetc,environment,OS etc
   public ExtentTest test;//to make entries of failed test cases inthe report
   String reportname;
   static String userdir="C:\\Users\\Sairam\\eclipse-workspace\\PetStoreAPIautomation";
   public static void getscreenshosts(WebDriver driver, ITestResult result) throws IOException 
   {
	   File local=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	   File src=new File(userdir+"//screenshots"+result.getMethod().getMethodName()+"_"+".jpg");
	   org.openqa.selenium.io.FileHandler.copy(local, src);
   }
   public void onStart(ITestContext context)
   {// on start of the execution of test acses we will get allthe info
	   String Timestamp=new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());//this will specify the time and date at which the repodt is generated
	   
	   reportname="Test-Report-"+Timestamp+".html";//repodt must be in HTML format
	   
	   sparkreporter=new ExtentSparkReporter(userdir+"\\ExtentReport\\"+reportname);
	   //location of the folder we are saving the report and attache the report name
	   //'.\\--->this reperesents the location of current project'
	   
	   sparkreporter.config().setDocumentTitle("Rest assured API automation Project");//title of pro.
	   sparkreporter.config().setReportName("PET STORE API Automation");//name of pro
	   sparkreporter.config().setTheme(Theme.DARK);// theme of report
	   
	   
	   extent=new ExtentReports();
	   extent.attachReporter(sparkreporter);
	   extent.setSystemInfo("Application","PET STORE API Automation");
	   extent.setSystemInfo("Operating System",System.getProperty("os.name"));
	   extent.setSystemInfo("User name",System.getProperty("user.name"));
	   extent.setSystemInfo("Environment","QA");
	   extent.setSystemInfo("user","Harshada");
   }
   
   public void onTestSuccess(ITestResult result)
   {//if test cases pass -this will get us the status of results
	   
	test=extent.createTest(result.getName());//this wil give us test case name
	test.assignCategory(result.getMethod().getGroups())  ;
	test.createNode(result.getName());
	test.log(Status.PASS, "Test Passed");
	 
   }
   public void onTestFailure(ITestResult result)
   {//if test cases failed -this will get us the status of results
	test=extent.createTest(result.getName());//this wil give us test case name
	test.assignCategory(result.getMethod().getGroups()) ;
	test.createNode(result.getName());
	test.log(Status.FAIL, "Test Failed");
	test.log(Status.FAIL,result.getThrowable().getMessage());
   }
   
   public void onTestSkipped(ITestResult result)
   {//if test cases skipped -this will get us the status of results
	   
	test=extent.createTest(result.getName());//this wil give us test case name
	test.assignCategory(result.getMethod().getGroups())  ;
	test.createNode(result.getName());
	test.log(Status.SKIP, "Test Skipped");
	test.log(Status.SKIP,result.getThrowable().getMessage());
   }
   
   public void onFinish(ITestContext context)
   {
	   extent.flush();
   }
}
