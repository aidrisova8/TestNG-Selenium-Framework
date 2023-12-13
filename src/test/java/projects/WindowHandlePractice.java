package projects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WindowHandlePractice extends TestBase {

    @Test
    public void WindowHandleTest() throws InterruptedException {
      driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//      String windowId=driver.getWindowHandle(); //return id of single browser window
//        System.out.println(windowId);
        driver.findElement(By.partialLinkText("OrangeHRM")).click();//opens another window-child window
        Set<String> windowIds=driver.getWindowHandles(); //return id's multiply browser windows

        // 1st method Iterator
    /*    Iterator<String> it=windowIds.iterator();
        String parentId= it.next();
        String childId=it.next();

        System.out.println(parentId);
        System.out.println(childId);*/

        //Second method using List

        List<String> windowIdList =new ArrayList<>(windowIds);
       /* String parentWindowId=windowIdList.get(0);
        String childWindowId=windowIdList.get(1);

        //How to use id's for switching
        driver.switchTo().window(parentWindowId);
        System.out.println(driver.getTitle());
        driver.switchTo().window(childWindowId);
        System.out.println(driver.getTitle()); */

        //For each loop
        for(String winid:windowIdList){
            String title=driver.switchTo().window(winid).getTitle();
            System.out.println(title);
        }

        for(String winid:windowIdList){
            String title=driver.switchTo().window(winid).getTitle();
            if(title.equals("OrangeHRM")){ //use or operator|| to close multiple windows
                driver.close();
            }
        }
    }

}
