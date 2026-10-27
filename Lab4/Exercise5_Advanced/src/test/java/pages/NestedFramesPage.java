package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NestedFramesPage extends BasePage {
    private final By middleFrameText = By.id("content");
    public NestedFramesPage(WebDriver d) { super(d); }
    public NestedFramesPage open() { navigateTo("https://the-internet.herokuapp.com/nested_frames"); return this; }

    public String getMiddleFrameText() {
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        String text = getText(middleFrameText);
        driver.switchTo().defaultContent(); // Trả về trang gốc
        return text;
    }
}
