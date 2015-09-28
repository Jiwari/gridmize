package base.grid;

import java.util.ArrayList;
import java.util.List;

import base.grid.action.ActionCell;
import base.grid.header.TableCellHeader;

public class Grid {
	private String selector;
	private int rowModifier = 0;
	private int columnModifier = 0;
	private Class<? extends TableCellHeader> gridHeader;

	public Grid(Class<? extends TableCellHeader> gridHeader, String selector) {
		this.selector = selector;
		this.gridHeader = gridHeader;
	}

	public void setRowModifier(int rowModifier) {
		this.rowModifier = rowModifier;
	}

	public void setColumnModifier(int columnModifier) {
		this.columnModifier = columnModifier;
	}

	public List<BaseCell> getColumn(TableCellHeader header) {
		List<BaseCell> cell = new ArrayList<BaseCell>();

		int rowLimit = getRowLimit();
		int column = header.getIndex() + columnModifier;

		for (int row = 1; row <= rowLimit; row++) {
			cell.add(getCellType(header, row + rowModifier, column));
		}
		return cell;
	}

	public List<BaseCell> getRow(int row) {
		List<BaseCell> cell = new ArrayList<BaseCell>();
		row += rowModifier;
		TableCellHeader[] header = gridHeader.getEnumConstants();
		for (int column = 1; column <= header.length; column++) {
			cell.add(getCellType(header[column], row, column + columnModifier));
		}
		return cell;
	}
	
	public BaseCell getCellType(TableCellHeader header, int row, int column) {
		if (header.isAction()) {
			return new ActionCell(selector, header.actionSelector(), row, column);
		} else {
			return new Cell(selector, row, column);
		}
	}

	public List<BaseCell> getRow(Cell row) {
		return getRow(row.getRow());
	}

	private int getRowLimit() {
		int column = 1 + columnModifier;
		int rowLimit = 1 + rowModifier;
		Cell cell = new Cell(selector, rowLimit, column);
		while (cell.getElement() != null) {
			rowLimit++;
			cell = new Cell(selector, rowLimit, column);
		}
		return --rowLimit;
	}
}