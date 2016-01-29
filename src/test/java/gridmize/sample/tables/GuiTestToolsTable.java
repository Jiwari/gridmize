package gridmize.sample.tables;

import base.grid.page.BaseTable;

public class GuiTestToolsTable extends BaseTable {
	// Page link
	// https://en.wikipedia.org/wiki/List_of_GUI_testing_tools
	
	public GuiTestToolsTable() {
		super(GuiTestToolsHeaderEnum.class, 
				"thead tr:nth-of-type(%s) th:nth-of-type(%s)", 
				"[class='wikitable sortable jquery-tablesorter'] tbody tr:nth-of-type(%s) td:nth-of-type(%s)");
	}
}
