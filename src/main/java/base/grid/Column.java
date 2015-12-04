package base.grid;

import java.util.ArrayList;
import java.util.List;

import base.grid.header.TableCellHeader;

public class Column {
	
	private TableCellHeader columnHeader;
	private List<BaseCell> column = new ArrayList<BaseCell>();

	
	public Column(TableCellHeader columnHeader) {
		this.columnHeader = columnHeader;	
	}
	
	public List<BaseCell> getColumn() {
		return column;
	}

	public void add(BaseCell column) {
		this.column.add(column);
	}
	
	public TableCellHeader getColumnName() {
		return this.columnHeader;
	}
}
