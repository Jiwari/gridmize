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

	void setGridColumnModifier(int columnModifier);

	void setGridRowModifier(int rowModifier);

	void setHeaderColumnModifier(int columnModifier);

	void setHeaderRowModifier(int rowModifier);
}
