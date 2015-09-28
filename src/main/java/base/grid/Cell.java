package base.grid;

import org.openqa.selenium.WebElement;

public class Cell extends BaseCell {

	public Cell(String selector, int row, int column) {
		super(selector, row, column);
	}

	public String getCellContent() {
		WebElement element = getElement();
		if (element == null)
			return null;
		return getElement().getText();
	}
}
