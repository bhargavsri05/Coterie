package coterie.TestComponents;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {

	@BeforeTest
	public void extentSetup(ExtentReports extent, ExtentSparkReporter spark) throws InterruptedException {
		Path path = Paths.get("target/extentReport");
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		extent = new ExtentReports();

		spark = new ExtentSparkReporter("target/extentReport/AutomationReports.html");
		spark.config().setTheme(Theme.DARK);
		extent.attachReporter(spark);

	}

}
