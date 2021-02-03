package testCom;

public class Info_Menu{
	String menu;
	String price;

	public Info_Menu() {
	}
	public Info_Menu(String menu, String price) {
		this.menu = menu;
		this.price = price;
	}
/***************setter,getter ¸Þ¼Òµå*******************/
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMenu() { 
		return menu;
	}
	public String getPrice() {
		return price;
	}
}	