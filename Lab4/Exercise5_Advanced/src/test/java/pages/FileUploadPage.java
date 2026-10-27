package pages;

import org.openqa.selenium.*;

public class FileUploadPage extends BasePage {
    private final By fileInput = By.id("file-upload");
    private final By uploadBtn = By.id("file-submit");
    private final By uploadedFiles = By.id("uploaded-files");

    public FileUploadPage(WebDriver d) { super(d); }

    public FileUploadPage open() {
        navigateTo("https://the-internet.herokuapp.com/upload");
        return this;
    }

    public void uploadFile(String absoluteFilePath) {
        driver.findElement(fileInput).sendKeys(absoluteFilePath);
        click(uploadBtn);
    }

    public String getUploadedFileName() {
        return getText(uploadedFiles);
    }
}