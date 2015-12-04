package base.grid;

import java.util.ArrayList;
import java.util.List;

import base.grid.action.ActionCell;
import base.grid.header.TableCellHeader;

public class Grid {
	private String selector;
	private int rowModifier = 0;
	private Class<? extends TableCellHeader> gridHeader;

	public Grid(Class<? extends TableCellHeader> gridHeader, String selector) {
		this.selector = selector;
		this.gridHeader = gridHeader;
	}

	public void setRowModifier(int rowModifier) {
		this.rowModifier = rowModifier;
	}

	public List<BaseCell> getColumn_Old(TableCellHeader header) {
		List<BaseCell> cell = new ArrayList<BaseCell>();
		int rowLimit = getRowLimit();
		for (int row = 1; row <= rowLimit; row++) {
			cell.add(getCellType(header, row + rowModifier));
		}
		return cell;
	}

	// TODO
	public Column getColumn(TableCellHeader header) {
		Column column = new Column(header);
		int rowLimit = getRowLimit();
		for (int rowIndex = 1; rowIndex <= rowLimit; rowIndex++) {
			column.add(getCellType(header, rowIndex + rowModifier));
		}
		return column;
	}

	// TODO
	public Row getRow(int rowIndex) {
		rowIndex += rowModifier;
		TableCellHeader[] header = gridHeader.getEnumConstants();
		Row row = new Row(rowIndex);
		for (int columnIndex = 1; columnIndex <= header.length; columnIndex++) {
			row.add(getCellType(header[columnIndex - 1], rowIndex));
		}
		return row;
	}

	public List<BaseCell> getRow_Old(int row) {
		List<BaseCell> cell = new ArrayList<BaseCell>();
		row += rowModifier;
		TableCellHeader[] header = gridHeader.getEnumConstants();
		for (int column = 1; column <= header.length; column++) {
			cell.add(getCellType(header[column - 1], row));
		}
		return cell;
	}

	public BaseCell getCellType(TableCellHeader header, int row) {
		if (header.isAction()) {
			return new ActionCell(selector, header.actionSelector(), row, header.getIndex());
		} else {
			return new Cell(selector, row, header.getIndex());
		}
	}

	public Row getRow(Cell row) {
		return getRow(row.getRow());
	}

	private int getRowLimit() {
		TableCellHeader[] header = gridHeader.getEnumConstants();
		int column = header[0].getIndex();
		int rowLimit = 1 + rowModifier;
		Cell cell = new Cell(selector, rowLimit, column);
		while (cell.getElement() != null) {
			rowLimit++;
			cell = new Cell(selector, rowLimit, column);
		}
		return --rowLimit;
	}
}