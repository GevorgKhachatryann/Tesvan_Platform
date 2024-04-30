package tests;
import base.setup;
import org.testng.annotations.Test;

public class test extends setup {
    @Test
    public void addTaskFunctionality() {
        driver.get("https://platform.tesvan.com/");
    }
}
