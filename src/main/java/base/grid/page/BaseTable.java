package base.grid.page;

import java.util.List;

import base.grid.BaseCell;
import base.grid.Grid;
import base.grid.header.CellHeader;
import base.grid.header.TableCellHeader;
import base.grid.header.TableHeader;
import base.grid.Cell;

public class BaseTable implements Table {

	private Class<? extends TableCellHeader> header;
	private TableHeader gridHeader;
	private Grid grid;
	private String gridSelector;

	public BaseTable(Class<? extends TableCellHeader> header, String headerSelector, String gridSelector) {
		this.header = header;
		this.gridSelector = gridSelector;
		gridHeader = new TableHeader(getHeaderClass(), headerSelector);
		grid = new Grid(this.header, this.gridSelector);
	}

	public Grid reloadGrid() {
		grid = new Grid(this.header, this.gridSelector);
		return grid;
	}

	public Grid getGrid() {
		return grid;
	}

	public List<BaseCell> getColumn(TableCellHeader header) {
		return grid.getColumn(header);
	}

	public List<BaseCell> getRow(Cell cell) {
		return grid.getRow(cell);
	}

	public TableCellHeader getHeader(String labelName) {
		TableCellHeader[] headerColumns = header.getEnumConstants();
		for (TableCellHeader headerColumn : headerColumns)
			if (headerColumn.getLabel().equalsIgnoreCase(labelName))
				return headerColumn;
		throw new RuntimeException("Coluna '" + labelName + "' não encontrada");
	}

	public CellHeader getHeader(TableCellHeader columnEnum) {
		return gridHeader.getCellHeader(columnEnum);
	}

	public Class<? extends TableCellHeader> getHeaderClass() {
		return this.header;
	}

	public List<BaseCell> getRowWithInfo(TableCellHeader columnEnum, String filter) {
		List<BaseCell> columns = grid.getColumn(columnEnum);
		return isTextDisplayed(columns, filter);
	}

	public boolean isInfoDisplayedOnColumn(TableCellHeader columnEnum, String filter) {
		if (getRowWithInfo(columnEnum, filter) != null)
			return true;
		return false;
	}

	public List<BaseCell> isTextDisplayed(List<BaseCell> column, String textExpected) {
		int rowInterator = 1;
		for (BaseCell cell : column) {
			String actualText = cell.getText();
			if (actualText != null) {
				if (actualText.equalsIgnoreCase(textExpected)) {
					return grid.getRow(rowInterator);
				}
			}
			rowInterator++;
		}
		return null;
	}

	public void setGridRowModifier(int rowModifier) {
		grid.setRowModifier(rowModifier);
	}

	public void setHeaderRowModifier(int rowModifier) {
		gridHeader.setRowModifier(rowModifier);
	}
}
