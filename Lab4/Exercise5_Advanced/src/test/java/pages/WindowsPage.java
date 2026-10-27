package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Set;

public class WindowsPage extends BasePage {
    private final By clickHereBtn = By.linkText("Click Here");
    private final By newWindowText = By.cssSelector("h3");
    public WindowsPage(WebDriver d) { super(d); }
    public WindowsPage open() { navigateTo("https://the-internet.herokuapp.com/windows"); return this; }

    public String openNewWindowAndGetText() {
        String originalWindow = driver.getWindowHandle();
        click(clickHereBtn);

        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String text = getText(newWindowText);
        driver.close(); // Đóng cửa sổ mới
        driver.switchTo().window(originalWindow); // Quay về cửa sổ cũ
        return text;
    }
}
