package base.grid;

import java.util.ArrayList;
import java.util.List;

public class Row {
	
	private int rowIndex;
	private List<BaseCell> row = new ArrayList<BaseCell>();

	public Row(int rowIndex) {
		this.rowIndex = rowIndex;		
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
}
