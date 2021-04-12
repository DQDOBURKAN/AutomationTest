package FinalTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeMethod;

public class DriverSetUp extends Password {

    public WebDriver msedge;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "E:\\Programs\\EdgeDriver\\msedgedriver.exe");
        this.msedge = new EdgeDriver();
        this.msedge.manage().window().maximize();
        this.msedge.get("http://shop.pragmatic.bg/admin/");
        msedge.findElement(By.id("input-username")).sendKeys("admin18");
        msedge.findElement(By.id("input-password")).sendKeys(getPassword());
        msedge.findElement(By.cssSelector(".btn-primary")).click();
    }
}
