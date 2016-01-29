package base.grid.page;

import java.util.List;

import base.grid.BaseCell;
import base.grid.Grid;
import base.grid.Row;
import base.grid.header.CellHeader;
import base.grid.header.TableCellHeader;
import base.grid.header.TableHeader;
import base.grid.page.exception.NoDataOnGridException;
import base.grid.Cell;
import base.grid.Column;

public class BaseTable {

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

	/**
	 * When the grid is instantiated it will map the content of the currently
	 * displayed grid. If the grid refreshes, the variable also needs to be
	 * refreshed.
	 * 
	 * @return {@link Grid}
	 */
	public Grid reloadGrid() {
		grid = new Grid(this.header, this.gridSelector);
		return grid;
	}

	public Grid getGrid() {
		return grid;
	}

	/**
	 * Gets the column of a given Cell.
	 * 
	 * @param cell
	 * @return {@link Column}
	 */
	public Column getColumn(TableCellHeader header) {
		return grid.getColumn(header);
	}

	/**
	 * Gets the row of a given Cell.
	 * 
	 * @param cell
	 * @return {@link Row}
	 */
	public Row getRow(Cell cell) {
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

	/**
	 * Searches for an information (filter) on a column (searchedColumn) and
	 * returns the row where the information was found
	 * 
	 * @param columnEnum
	 * @param filter
	 * @return {@link Row}
	 */
	public Row getRowWithInfo(TableCellHeader columnEnum, String filter) {
		Column column = grid.getColumn(columnEnum);
		return isTextDisplayed(column, filter);
	}

	/**
	 * Searches for an information (filter) on a column (searchedColumn) and
	 * returns a cell of the given column (returnColumn)
	 * 
	 * @param searchedColumn
	 * @param filter
	 * @param returnColumn
	 * @return {@link BaseCell}
	 * @throws NoDataOnGridException
	 */
	public BaseCell getColumnWithInfo(TableCellHeader searchedColumn, String filter, TableCellHeader returnColumn) {
		Column columns = grid.getColumn(searchedColumn);
		Row row = isTextDisplayed(columns, filter);
		if (row == null)
			throw new NoDataOnGridException(
					"No data was found on the grid with the given information." + "\nHeader: " + header.getName()
							+ "\nSearched column: " + searchedColumn.getLabel() + "\nSearched information: " + filter);
		BaseCell cellFound = row.getRow().get(returnColumn.getIndex() - 1);
		return cellFound;
	}

	public boolean isInfoDisplayedOnColumn(TableCellHeader columnEnum, String filter) {
		if (getRowWithInfo(columnEnum, filter) != null)
			return true;
		return false;
	}

	public Row isTextDisplayed(Column column, String textExpected) {
		int rowInterator = 1;
		List<BaseCell> columnCells = column.getColumn();
		for (BaseCell cell : columnCells) {
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

	/**
	 * Set a 'modifier' for the grid. It should be used when the Grid content
	 * does not start on index 1. IE: A grid content starts on the index 4, so
	 * you have to set a modifier to 3, because 1 is already the default that
	 * will be added to the modifier
	 * 
	 * @param rowModifier
	 */
	public void setGridRowModifier(int rowModifier) {
		grid.setRowModifier(rowModifier);
	}

	/**
	 * Set a 'modifier' for the Header. It should be used when the header does
	 * not start on index 1. IE: A header starts on the index 4, so you have to
	 * set a modifier to 3, because 1 is already the default that will be added
	 * to the modifier
	 * 
	 * @param rowModifier
	 */
	public void setHeaderRowModifier(int rowModifier) {
		gridHeader.setRowModifier(rowModifier);
	}
}
