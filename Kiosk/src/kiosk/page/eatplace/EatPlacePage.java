package kiosk.page.eatplace;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import kiosk.MainFrame;
import kiosk.page.KioskGuidePanel;
import kiosk.page.ImageTextButton;
import kiosk.page.KioskPage;
import kiosk.page.confirm.ConfirmPage;
import kiosk.page.order.OrderPage;
import kiosk.page.order.OrderPlace;
import kiosk.page.payment.place.AlarmSelectPage;
import kiosk.page.welcome.WelcomePage;
import kiosk.page.welcome.WelcomePage2;

/**
 * Class Role : 식사장소 데이터 및 식사장소/언어 선택 화면을
 * 담당한다.
 */
public class EatPlacePage extends KioskPage {

	private final int MIDDLE_PANEL_WIDTH = KioskPage.PAGE_WIDTH * 4 / 5;
	private final int MIDDLE_PANEL_HEIGHT = KioskPage.PAGE_HEIGHT * 2 / 5;

	private final KioskGuidePanel PLACE_SELECT_GUIDE_PANEL = new KioskGuidePanel("식사하실 장소를 선택해 주세요", 0, 2);
	private final ImageTextButton EAT_BUTTON = new ImageTextButton("매장 식사", new ImageIcon("image/icon_eat.jpg"));
	private final ImageTextButton TAKE_BUTTON = new ImageTextButton("테이크 아웃(포장)", new ImageIcon("image/icon_take.jpg"));
	private static EatPlace eatplace;
	
	public EatPlacePage() {
		initPage();		// 백그라운드(배경)
		initPlaceGuidePanel();	
		setListeners();
	}

	private void initPage() {		// 배경화면 설정
		this.setBackgroundImg("image/background.png");
		this.showBackgroundImg(true);
		this.showBackButton(true);
	}

	private void initPlaceGuidePanel() {		// 가이드패널 설정
		PLACE_SELECT_GUIDE_PANEL.addItem(EAT_BUTTON, TAKE_BUTTON);
		Component placeSelectGuideComp = PLACE_SELECT_GUIDE_PANEL.getPanel();
		placeSelectGuideComp.setSize(MIDDLE_PANEL_WIDTH, MIDDLE_PANEL_HEIGHT);
		placeSelectGuideComp.setLocation((KioskPage.PAGE_WIDTH - MIDDLE_PANEL_WIDTH) / 2, KioskPage.PAGE_HEIGHT / 4);
		
		this.add(placeSelectGuideComp);
	}

	private void setListeners() {		// 버튼 클릭시 액션 설정(취식인지 포장인지 구분을 위함)
		//BACK_BUTTON.addActionListener((args) -> MainFrame.attachPanel(new WelcomePage()));
		BACK_BUTTON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.attachPanel(new OrderPage());
			}
		});
		EAT_BUTTON.addActionListener((args) -> {
			OrderPlace.getInstance().setEatPlace(EatPlace.매장식사);	// 주문장소 선택페이지로 넘길 글자
			setPlace(EatPlace.매장식사);			// 주문확인페이지로 넘길 글자
			MainFrame.attachPanel(new ConfirmPage(false));
		});
		TAKE_BUTTON.addActionListener((args) -> {
			OrderPlace.getInstance().setEatPlace(EatPlace.포장);
			setPlace(EatPlace.포장);
			MainFrame.attachPanel(new ConfirmPage(false));
		});
	}
	
	public void setPlace(EatPlace eatplace) {		// eatplace 저장
		this.eatplace = eatplace;
	}
	public static EatPlace getPlace() {			// eatplace 출력
		return eatplace;
	}
	public static int sendserver( ) {
		if(getPlace() == EatPlace.매장식사)
			return 0;
		else
			return 1;
	}

	
}