package kiosk.page.order;

import kiosk.page.eatplace.EatPlace;
import kiosk.page.payment.place.AlarmSelect;

/**
 * Class Role : 주문장소를 선택 데이터를 보관 관리한다.
 */
public class OrderPlace {
	private static final OrderPlace UNIQUE_INSTANCE = new OrderPlace();
	
	private String eatPlace;
	private static String alarmSelect;
	
	private OrderPlace() {
		
	}
	
	public static OrderPlace getInstance() {
		return UNIQUE_INSTANCE;
	}
	
	public String getEatPlace() {
		return eatPlace;
	}
	
	public void setEatPlace(EatPlace place) {
		
		this.eatPlace = place.toString(); 
	}
	
	public static String getAlarmSelect() {
		return alarmSelect;
	}
	public static int sendserver() {
		if(getAlarmSelect() == "APP")
			return 0;
		else
			return 1;
	}
	
	public void setAlarmSelect(AlarmSelect place) {
		this.alarmSelect = place.toString();
	}
	
	public void init() {
		eatPlace = null;
		alarmSelect = null;
	}
}