package business;

//getters and setters for product
public class Product {
	public static final int SIZE_P_ID = 25;
	public static final int SIZE_P_NAME = 25;
	public static final int SIZE_P_DESC = 50;
	public static final int SIZE_P_QUANT = 4;
	public static final int SIZE_P_UNITPRICE = 4;
	public static final int RECORD_SIZE = ( SIZE_P_ID * 2 ) + ( SIZE_P_NAME * 2 ) + ( SIZE_P_DESC * 2 ) + ( SIZE_P_QUANT ) + ( SIZE_P_UNITPRICE );
	private String pId;
	private String pName;
	private String pDesc;
	private int pQuantity;
	private int pUnitPrice;
	
	public String getPID() {
		return pId;
	}
	public void setPID(String pId) {
		this.pId = pId;
	}
	
	public String getPName() {
		return pName;
	}
	public void setPName(String pName) {
		this.pName = pName;
	}
	
	public String getPDesc() {
		return pDesc;
	}
	public void setPDesc(String pDesc) {
		this.pDesc = pDesc;
	}
	
	public int getPQuantity() {
		return pQuantity;
	}
	public void setPQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}
	
	public int getPUnitPrice() {
		return pUnitPrice;
	}
	public void setPUnitPrice(int pUnitPrice) {
		this.pUnitPrice = pUnitPrice;
	}
	

	public Product(String pId, String pName, String pDesc, int pQuantity, int pUnitPrice) {
		this.pId = pId;
		this.pName = pName;
		this.pDesc = pDesc;
		this.pQuantity = pQuantity;
		this.pUnitPrice = pUnitPrice;
	}
	
	public Product() {
		
	}
	
	public String toString() {
		return pId + " [ " + pName + " ] "  + " [ " + pDesc + " ] "  + " [ " + pQuantity + " ] "  + " [ " + pUnitPrice + " ]";
	}

}
