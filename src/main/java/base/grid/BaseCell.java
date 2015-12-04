package base.grid;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public abstract class BaseCell {

	private String selector;
	private static WebDriver driver;
	private int row;
	private int column;
	private WebElement element;
	
	protected int getRow() {
		return row;
	}

	protected int getColumn() {
		return column;
	}

	public BaseCell(String selector, int row, int column) {
		this.selector = selector;
		this.row = row;
		this.column = column;
	}
	
	public BaseCell(String selector, String actionSelector, int row, int column) {
		this.selector = selector + actionSelector;
		this.row = row;
		this.column = column;
	}

	protected WebElement getElement() {
		try {
			waitScreenLoader();
			element = null;
			element = getDriver().findElement(By.cssSelector(getFormatedSelector()));
			focusElement();
		} catch (NoSuchElementException e) {
		}
		return element;
	}
	
	private String getFormatedSelector(){
		String cellSelector = String.format(selector, row, column);
		return cellSelector;
	}

	private void focusElement() {
		JavascriptExecutor script = (JavascriptExecutor) getDriver();
		String focusScript = "$(\"%s\").focus()";
		script.executeScript(String.format(focusScript, getFormatedSelector()));
	}
	
	public String getText() {
		WebElement element = getElement();
		if (element == null)
			return null;
		return getElement().getText();
	}
	
	public void click() {
			By bySelector = By.cssSelector(getFormatedSelector());
			new FluentWait<WebDriver>(getDriver())
					.withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(100, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.elementToBeClickable(bySelector))
					.click();
			waitScreenLoader();
	}

	protected void waitScreenLoader() {
		//If there is a screen loader, add its CssPath here.
		// With this, no action will be taken until the loader has closed
		String loaderCssPath = "";
		waitElementClose(loaderCssPath, 120);
	}

	private void waitElementClose(String Css, int timeout) {
		new FluentWait<WebDriver>(getDriver()).withTimeout(100, TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(Css)));
	}

	private static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		BaseCell.driver = driver;
	}
}
