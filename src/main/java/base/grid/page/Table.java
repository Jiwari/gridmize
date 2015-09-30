package base.grid.page;

import java.util.List;

import base.grid.BaseCell;
import base.grid.Grid;
import base.grid.header.CellHeader;
import base.grid.header.TableCellHeader;

public interface Table {

	TableCellHeader getHeader(String labelName);

	CellHeader getHeader(TableCellHeader columnEnum);

	boolean isInfoDisplayedOnColumn(TableCellHeader columnEnum, String filter);

	List<BaseCell> isTextDisplayed(List<BaseCell> cells, String filter);

	List<BaseCell> getRowWithInfo(TableCellHeader columnEnum, String filter);

	Grid getGrid();

	Grid reloadGrid();

	void setGridRowModifier(int rowModifier);

	void setHeaderRowModifier(int rowModifier);
}
