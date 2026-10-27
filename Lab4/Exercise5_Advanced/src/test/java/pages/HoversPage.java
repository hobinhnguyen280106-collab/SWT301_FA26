package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HoversPage extends BasePage {
    private final By firstAvatar = By.cssSelector(".figure:nth-child(3) img");
    private final By firstUserName = By.cssSelector(".figure:nth-child(3) h5");
    public HoversPage(WebDriver d) { super(d); }
    public HoversPage open() { navigateTo("https://the-internet.herokuapp.com/hovers"); return this; }

    public String hoverAndGetUserName() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(firstAvatar)).perform();
        return getText(firstUserName);
    }
}
