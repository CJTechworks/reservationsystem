//Package name
package appointmentbooking;

//Import statements
import javax.swing.table.*;
import java.util.*;


class BasicTableModel extends AbstractTableModel 
{
	private ArrayList names;	
	private ArrayList data;		
		
	public BasicTableModel() 
        {
		super();
		
		names = new ArrayList();
		data = new ArrayList();
	}


	// Basic Model overrides
	public String getColumnName(int col) {
		return (String) names.get(col);
	}
	public int getColumnCount() { return(names.size()); }
	public int getRowCount() { return(data.size()); }
	public Object getValueAt(int row, int col) {
		ArrayList rowList = (ArrayList) data.get(row);
		String result = null;
		if (col<rowList.size()) {
			result = (String)rowList.get(col);
		}
		
		// _apparently_ it's ok to return null for a "blank" cell
		return(result);
	}
	
	
	

	// Deletes the given row
	public void deleteRow(int row) {
		if (row == -1) return;
		DBHandler h = new DBHandler();
		h.delete(data.get(row).toString());
		
		data.remove(row);
		fireTableRowsDeleted(row, row);
		
	}
	
	

	public ArrayList stringToList(String string) {
	    
		StringTokenizer tokenizer = new StringTokenizer(string, "\t", true);
		ArrayList row = new ArrayList();
		String elem = null;
		String last = null;
		while(tokenizer.hasMoreTokens()) {
			last = elem;
			elem = tokenizer.nextToken();
			if (!elem.equals("\t")) row.add(elem);
			else if (last.equals("\t")) row.add("");
			
		}
		if (elem.equals("\t")) row.add(""); 
		
		return(row);
	}
	
	
	public void load(String text) {
		ArrayList first = stringToList("Appointments");
		names = first;

		String line;
		 data = new ArrayList();
		String[] temp = text.split("\n");
		 for(int i=0;i<temp.length;i++) {
			data.add(stringToList(temp[i]));
			
		}
	}



	 	
}

