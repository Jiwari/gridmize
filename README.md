# Gridmize
Gridmize is a Selenium WebDriver based project where the objective is to create a simple way to create and get grids by just giving Gridmize the selector and an Enum where the columns are specified. 

##Using Gridmize
To use Gridmize you will have to follow three steps
*	Create an Enum to map the headers
*	Create a Grid class
*	Use the grid


###Creating the header
Create a Enum for the grid to map the grid header, that will be used to create selectors later.
For your Enum, implement the TableCellHeader, and set the parameters as below.

```java
public enum GridEnum implements TableCellHeader {
	COLUMN_ONE(1, "My First Column", false, "");
	
	private int index;
	private String label;
	private boolean isAction;
	private String actionSelector;

	private GridEnum(int index, String label, boolean isAction, String actionSelector) {
		this.index = index;
		this.label = label;
		this.isAction = isAction;
		this.actionSelector = actionSelector;		
	}

	public int getIndex() {
		return index;
	}

	public String getLabel() {
		return label;
	}

	public boolean isAction() {
		return isAction;
	}

	public String actionSelector() {
		return actionSelector;
	}

	public TableCellHeader[] getValues() {
		return values();
	}
```

Now you just have to create a Grid class for your Grid

```java
public class MyGrid extends BaseTable {

	public MyGrid() {
		super(GridEnum.class, "gridSelector", "headerSelector");
	}
}
```

As for the selectors, you will have to provide a String to the grid that can be formated.
\t "tbody > tr:nth-of-type(%s) > th:nth-of-type(%s)" 
\t "thead > tr:nth-of-type(%s) > th:nth-of-type(%s)"

###Modifiers
Sometimes, your table doesn't start on the index 1 x 1, so you will have to set 'modifiers', these will be added to the index 1 (default).

```java
public MyGrid() {
		super(GridEnum.class, "gridSelector", "headerSelector");
		setGridColumnModifier(1);
		setGridRowModifier(3);
		setHeaderColumnModifier(1);
		setHeaderRowModifier(3);
	}
```

#Note
The WebDriver is not by default, so you will have to add your WebDriver to the BaseCell class.