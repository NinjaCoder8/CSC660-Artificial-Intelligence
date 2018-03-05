
public class Index {

	private String value;
	private int row;
	private int column;
	private int cost;
	
	public Index(int i, int j, String val){
		this.value = val;
		this.row =i;
		this.column =j;
		this.cost = -9999;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int i) {
		this.row = i;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int j) {
		this.column = j;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public String toString(){
		return this.value;
	}
	
}
