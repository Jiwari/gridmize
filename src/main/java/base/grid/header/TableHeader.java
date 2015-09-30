package base.grid.header;

import java.util.ArrayList;
import java.util.List;

public class TableHeader {

	private Class<? extends TableCellHeader> header;
	private String selector;
	private int rowModifier = 0;

	public TableHeader(Class<? extends TableCellHeader> header, String selector) {
		this.header = header;
		this.selector = selector;
	}

	public void setRowModifier(int rowModifier) {
		this.rowModifier = rowModifier;
	}

	private List<CellHeader> getHeaders() {
		List<CellHeader> cellsHeader = new ArrayList<CellHeader>();
		TableCellHeader[] headerColumns = header.getEnumConstants();
		int row = 1 + rowModifier;
		int column;
		for (TableCellHeader headerColumn : headerColumns) {
			column = headerColumn.getIndex();
			cellsHeader.add(new CellHeader(selector, row, column, headerColumn));
		}
		return cellsHeader;
	}

	public List<CellHeader> getCellsHeader() {
		return getHeaders();
	}

	public CellHeader getCellHeader(TableCellHeader cellHeader) {
		return getHeaders().get(cellHeader.getIndex()-1);
	}
}
