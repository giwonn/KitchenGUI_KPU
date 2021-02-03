package kiosk.page.confirm;

public class OrderData {
	final int BINARY;
	final String MENU_NAME;
	final int begin_PRICE;
	final int begin_K_CAL;
	int PRICE;
	int K_CAL;
	int AMOUNT = 1;
	String Temp;
	String Size;
	
	public OrderData(int binary, String menuName, int price, int kCal, String size, String temp) {
		this.BINARY = binary;
		this.MENU_NAME = menuName;
		this.begin_PRICE = price;
		this.begin_K_CAL = kCal;
		this.Size = size;
		this.Temp = temp;
		PRICE = begin_PRICE * AMOUNT;
		K_CAL = begin_K_CAL * AMOUNT;
	}
	public String getMenu() { 
		return MENU_NAME;
	}
	public void addAmount() {
		AMOUNT++;
		PRICE = begin_PRICE * AMOUNT;
		K_CAL = begin_K_CAL * AMOUNT;
	}
	public int getAmount() {
		return AMOUNT;
	}
	public int getPrice() {
		return PRICE;
	}
	public int getkCal() {
		return K_CAL;
	}
	public int getBinary() {
		return BINARY;
	}
	
	public void setTemp(String Temp) {
		this.Temp = Temp; 
	}
	public String getTemp() {
		return Temp;
	}
	public int sendTemp() {
		if(Temp.equals("Hot") || Temp.equals("0"))
			return 0;
		else
			return 1;
	}
	public void setSize(String Size) {
		this.Size = Size; 
	}
	public String getSize() {
		return Size;
	}
	public int sendSize() {
		if(Size.equals("Regular") || Size.equals("0"))
			return 0;
		else
			return 1;
	}
	
	@Override
	public String toString() {
		if(Size.equals("0") && Temp.equals("0"))
			return MENU_NAME + " || " + "수량 " + AMOUNT + " || " + "가격 ￦" + PRICE + " || " + K_CAL + "KCal";
		else if(Temp.equals("0"))
			return MENU_NAME + "(" + Size + ")" + " || " + "수량 " + AMOUNT + " || " + "가격 ￦" + PRICE + " || " + K_CAL + "KCal";
		else
			return MENU_NAME + "(" + Size + ")" + "(" + Temp + ")" + " || " + "수량 " + AMOUNT + " || " + "가격 ￦" + PRICE + " || " + K_CAL + "KCal";
	}
}