package base.grid.header;

public interface TableCellHeader {

	int getIndex();
	
	String getLabel();
	
	boolean isAction();
	
	String actionSelector();

	TableCellHeader[] getValues();
	
}
