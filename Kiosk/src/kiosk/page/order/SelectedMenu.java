package kiosk.page.order;

/**
 * Class Role : 선택메뉴 데이터를 제공한다.
 */
public class SelectedMenu {
	public static int orderPrice = 0;	// 주문금액
	public static int orderAmount = 0;	// 주문수량
	public static int totalKCal = 0;	// 총 칼로리
	
	private SelectedMenu() {
		
	}
	
	public static void addOrder(int price, int amount, int kCal) {
		orderPrice += price;
		orderAmount += amount;
		totalKCal += kCal;
	}
	
	public static void init() {
		orderPrice = 0;
		orderAmount = 0;
		totalKCal = 0;
	}
}