package base.grid;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public abstract class BaseCell {

	private String selector;
	private WebDriver driver = null;
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
			esperaSpinLoad();
			element = null;
			element = driver.findElement(By.cssSelector(getFormatedSelector()));
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
		JavascriptExecutor script = (JavascriptExecutor) driver;
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
			new FluentWait<WebDriver>(driver)
					.withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(100, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.elementToBeClickable(bySelector))
					.sendKeys(Keys.ENTER);
			esperaSpinLoad();
	}

	protected void esperaSpinLoad() {
		esperaFecharElemento("[class='body-loader active']", 120);
	}

	private void esperaFecharElemento(String Css, int timeout) {
		new FluentWait<WebDriver>(driver).withTimeout(100, TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(Css)));
	}
}
