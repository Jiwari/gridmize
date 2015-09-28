package base.grid.action;

import org.openqa.selenium.WebElement;

public class Action {
	
	private WebElement element;
	
	public Action(WebElement element){
		this.element = element;
	}
	
	public void click() {
		element.click();
	}
}
