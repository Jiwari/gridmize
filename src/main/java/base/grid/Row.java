package base.grid;

import java.util.ArrayList;
import java.util.List;

import base.grid.header.TableCellHeader;
import base.grid.page.exception.NoDataOnGridException;

public class Row {

	private int rowIndex;
	private List<BaseCell> row = new ArrayList<BaseCell>();
	private int size;
	private Class<? extends TableCellHeader> header;

	public Row(int rowIndex, Class<? extends TableCellHeader> header) {
		this.rowIndex = rowIndex;
		this.header = header;
	}

	public List<BaseCell> getRow() {
		return row;
	}

	public void add(BaseCell row) {
		this.row.add(row);
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getSize() {
		return size;
	}

	/** Gets the specified column of the current row.
	 * @param column
	 * @return {@link BaseCell}
	 */
	public BaseCell getColumn(TableCellHeader column) {
		if (!column.getClass().getSimpleName().equals(header.getSimpleName())) {
			throw new RuntimeException("[%s] is not from the same Enum of the row!");
		}
		TableCellHeader[] columns = column.getValues();
		int index = 0;
		for (TableCellHeader currentColumn : columns) {
			if (currentColumn.getLabel().equals(column.getLabel())) {
				row.get(index);
			}
			index++;
		}
		throw new NoDataOnGridException(
				String.format("The column [%s] was not found on the row [%s]", column.getLabel(), rowIndex)
				+ "Table header: " + header.getName());
	}
}
