package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddRemoveElementsPage extends BasePage {
    private final By addBtn = By.xpath("//button[text()='Add Element']");
    private final By deleteBtns = By.className("added-manually");
    public AddRemoveElementsPage(WebDriver d) { super(d); }
    public AddRemoveElementsPage open() { navigateTo("https://the-internet.herokuapp.com/add_remove_elements/"); return this; }

    public void addElements(int count) {
        for (int i = 0; i < count; i++) { click(addBtn); }
    }
    public int getDeleteButtonsCount() {
        return driver.findElements(deleteBtns).size();
    }
}