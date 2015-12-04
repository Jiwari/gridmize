package base.grid.page;

import base.grid.Column;
import base.grid.Grid;
import base.grid.Row;
import base.grid.header.CellHeader;
import base.grid.header.TableCellHeader;

public interface Table {

	TableCellHeader getHeader(String labelName);

	CellHeader getHeader(TableCellHeader columnEnum);

	boolean isInfoDisplayedOnColumn(TableCellHeader columnEnum, String filter);

	Row isTextDisplayed(Column column, String filter);

	Row getRowWithInfo(TableCellHeader columnEnum, String filter);

	Grid getGrid();

	Grid reloadGrid();

	void setGridRowModifier(int rowModifier);

	void setHeaderRowModifier(int rowModifier);
}
