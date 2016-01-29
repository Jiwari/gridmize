package gridmize.sample.tables;

import base.grid.header.TableCellHeader;

public enum GuiTestToolsHeaderEnum implements TableCellHeader {

	NAME(1, "Name", true, " a"),
	TEST_SYSTEM_REQ(2, "Testing system requirement"),
	SYSTEM_UNDER_TEST_REQ(3, "System under test requirement"),
	COMPANY(4, "Company"),
	LICENSE(5, "License", true, " a"),
	GUI_TEST(6, "GUI Test"),
	AUTOMATION(7, "Automation"),
	CURRENT_VERSION(8, "Current version"),
	REFERENCE(9, "Reference"),
	STATUS(10, "Status");
	
	private int index;
	private String label;
	private boolean isAction;
	private String actionSelector;

	private GuiTestToolsHeaderEnum(int index, String label, boolean isAction, String actionSelector) {
		this.index = index;
		this.label = label;
		this.isAction = isAction;
		this.actionSelector = actionSelector;
	}

	private GuiTestToolsHeaderEnum(int index, String label) {
		this.index = index;
		this.label = label;
		this.isAction = false;
		this.actionSelector = "";
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
}
