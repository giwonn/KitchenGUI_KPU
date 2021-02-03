package kiosk.page.order;


/**
 *
 * 장바구니에 표시할 목록들을 담아준다.
 * CartPanel.MenuList에 arraylist로 담아준다.
 *
 */
public class CartMenu {
	String menuname;
	int menunum;
	int price;
	
	public CartMenu(String menuname, int menunum, int price) {
		this.menuname = menuname;
		this.menunum = menunum;
		this.price = price;
	}
	
	public String getmenuname() {
		return menuname;
	}
	public int getmenunum() {
		return menunum;
	}
	public int getprice() {
		return price;
	}
}
