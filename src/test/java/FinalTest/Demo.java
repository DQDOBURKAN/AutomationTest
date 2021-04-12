package FinalTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Demo extends DriverSetUp {

    @Test
    public void voucherDemo () {
        /** Sales menu */
        msedge.findElement(By.xpath("//*[@id=\"menu-sale\"]/a")).click();
        WebDriverWait wait = new WebDriverWait(msedge, 10);
        /** Gift Vouchers menu */
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#collapse4 > li:nth-child(4) > a")));
        msedge.findElement(By.cssSelector("#collapse4 > li:nth-child(4) > a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"collapse4-3\"]/li[1]/a")));
        msedge.findElement(By.xpath("//*[@id=\"collapse4-3\"]/li[1]/a")).click();

        /** add all existing vouchers to a list */
        List<String> codes = new ArrayList<>();
        List<WebElement> allVouchers = msedge.findElements(By.className("text-left"));
        for (int i = 0; i < allVouchers.size(); i++) {
            codes.add(allVouchers.get(i).getText());
        }

        Actions myActions = new Actions(msedge);

        /** assert check for my voucher */
        String a = "20MORE";
        if (codes.contains(a)) {
            System.out.println("Ima go");

        } else {
            System.out.println("code " + a + " is not in the list");
        }

        try {
            Assert.assertTrue(codes.contains(a),a +" is not in the list");
        }catch (Throwable e){
            System.out.println("you must add the Code");
        }

        /** adding my gift voucher */
        msedge.findElement(By.xpath("//*[@data-original-title='Add New']")).click();
        this.msedge.findElement(By.name("code")).sendKeys("20MORE");
        this.msedge.findElement(By.name("from_name")).sendKeys("A");
        this.msedge.findElement(By.name("from_email")).sendKeys("A@gmail.com");
        this.msedge.findElement(By.name("to_name")).sendKeys("B");
        this.msedge.findElement(By.name("to_email")).sendKeys("B@gmail.com");
        this.msedge.findElement(By.name("amount")).sendKeys("100");
        this.msedge.findElement(By.xpath("//*[@data-original-title='Save']")).click();
        msedge.findElement(By.xpath("//*[@id=\"collapse4-3\"]/li[1]/a")).click();

        /** deleting my gift voucher */
        WebElement myVoucher1 = msedge.findElement(By.xpath("//*[contains(text(),'20MORE')]/../td[1]/input[@type='checkbox']"));
        String myVoucherVerify = msedge.findElement(By.xpath("//*[contains(text(),'20MORE')]")).getText();
        /** verifying voucher exists */
        Assert.assertEquals(myVoucherVerify,a);
        myActions.click(myVoucher1).perform();
        WebElement myVoucherDelete = msedge.findElement(By.xpath("//*[@data-original-title=\"Delete\"]"));
        myActions.click(myVoucherDelete).perform();
        /** handle alert */
        msedge.switchTo().alert().accept();

    }


}
