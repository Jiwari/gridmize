package gridmize.sample.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import base.grid.BaseCell;
import gridmize.sample.tables.GuiTestToolsHeaderEnum;
import gridmize.sample.tables.GuiTestToolsTable;

public class TableTest {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Renner.Config/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		BaseCell.setDriver(driver);
		driver.get("https://en.wikipedia.org/wiki/List_of_GUI_testing_tools");		
		Thread.sleep(3000);
		
		GuiTestToolsTable table = new GuiTestToolsTable();
		String company = table
				.getColumnWithInfo(GuiTestToolsHeaderEnum.NAME, "Selenium", GuiTestToolsHeaderEnum.COMPANY)
				.getText();
		
		System.out.println("Selenium company: " + company);

		driver.close();
		driver.quit();
	}
}
