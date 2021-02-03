package kiosk.page.confirm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import kiosk.MainFrame;
import kiosk.page.KioskGuidePanel;
import kiosk.page.KioskGuidePanel2;
import kiosk.page.KioskPage;
import kiosk.page.eatplace.EatPlacePage;
import kiosk.page.order.MenuButton;
import kiosk.page.order.OrderPage;
import kiosk.page.thank.ThankPage;
import kiosk.page.welcome.OrderButton;
/**
 * Class Role : 주문확인 페이지
 */
public class ConfirmPage extends KioskPage {
	Font font = new Font("arian", Font.BOLD, 25);	// 폰트 설정
	
	/* 중간패널 사이즈 */
	private final int MIDDLE_PANEL_WIDTH = KioskPage.PAGE_WIDTH * 4 / 5;
	private final int MIDDLE_PANEL_HEIGHT = KioskPage.PAGE_HEIGHT * 2 / 5;
	
	// 가운데 패널
	private final KioskGuidePanel ORDER_CONFIRM_GUIDE_PANEL = new KioskGuidePanel("주문을 확인해주세요", 0, 1);
	private final KioskGuidePanel CHOOSE_PLACE_GUIDE_PANEL = new KioskGuidePanel("포장을 선택해주세요", 0, 1);
	
	// 중하단 패널
	private final KioskGuidePanel2 NUMBER_PANEL = new KioskGuidePanel2();
	
	// 하단 패널
	private final KioskGuidePanel YES_NO_SELECT_PANEL = new KioskGuidePanel(0, 2);
	private final ConfirmButton NO_BUTTON = new ConfirmButton("취소"); 
	private final OrderButton YES_BUTTON = new OrderButton("확인");
	
	private static String number[] = new String[3];
	JLabel label, hyphen1, hyphen2;
	
	ChoosePlace chooseplace = new ChoosePlace();
	MileagePanel mileage = new MileagePanel();
	
	public ConfirmPage(boolean show) {
		orderdata();
		initConfirmPage();
		initChoosePanel();
		initmileagePanel();
		initOrderTotalListPanel();
		initYesNoSelectPanel();
		setListener();
	}
	
	private void orderdata() {		// 뭘 주문했는지 OrderTotalDataPanel을 이용해서 불러온다.
//		 OrderTotalDataPanel ORDER_TOTAL_LIST_PANEL = new OrderTotalDataPanel(EatPlacePage.getPlace(), CartPanel.Save_Menu);
		
		OrderTotalDataPanel ORDER_TOTAL_LIST_PANEL = new OrderTotalDataPanel(EatPlacePage.getPlace(), MenuButton.info);
		ORDER_CONFIRM_GUIDE_PANEL.addItem(ORDER_TOTAL_LIST_PANEL);
		
			
	}
	
	private void initConfirmPage() {
		this.setBackgroundImg("image/background_panel.png");
		this.showBackgroundImg(true);
		this.showBackButton(false);
	}
	
	private void initmileagePanel() {
		mileage.setSize(MIDDLE_PANEL_WIDTH *24/50, MIDDLE_PANEL_HEIGHT*4/5);
		mileage.setLocation(KioskPage.PAGE_WIDTH *3/5, KioskPage.PAGE_HEIGHT *23/50 + 15);
		mileage.setBackground(Color.WHITE);
		

		this.add(mileage);
		
	}

	private void initChoosePanel() {
//		chooseplace.setSize(MIDDLE_PANEL_WIDTH *24/50, MIDDLE_PANEL_HEIGHT / 2);
//		chooseplace.setLocation(KioskPage.PAGE_WIDTH *3/5, KioskPage.PAGE_HEIGHT * 11/50);

		chooseplace.setBackground(Color.WHITE);
//		chooseplace.setBorder(new LineBorder(Color.BLACK,1));
		
		CHOOSE_PLACE_GUIDE_PANEL.setTitleColor(Color.GREEN, new Font("arian", Font.BOLD, 40));
		Component orderConfirmGuideComp = CHOOSE_PLACE_GUIDE_PANEL.getPanel();
		orderConfirmGuideComp.setSize(MIDDLE_PANEL_WIDTH *24/50, MIDDLE_PANEL_HEIGHT *19/50);
		orderConfirmGuideComp.setLocation(KioskPage.PAGE_WIDTH *3/5, KioskPage.PAGE_HEIGHT * 11/50);
		CHOOSE_PLACE_GUIDE_PANEL.addItem(chooseplace);
		
		this.add(CHOOSE_PLACE_GUIDE_PANEL.getPanel());
	}
	public static void initPhoneNumber() {
		for(int i=0;i<MileagePanel.number.length;i++)
			MileagePanel.number[i] = "";
	}
	private void initOrderTotalListPanel() {
		ORDER_CONFIRM_GUIDE_PANEL.setTitleColor(Color.GREEN, new Font("arian", Font.BOLD, 40));
		
		Component orderConfirmGuideComp = ORDER_CONFIRM_GUIDE_PANEL.getPanel();
		orderConfirmGuideComp.setSize(MIDDLE_PANEL_WIDTH *2/3, MIDDLE_PANEL_HEIGHT * 4/3);
		orderConfirmGuideComp.setLocation(KioskPage.PAGE_WIDTH / 50, KioskPage.PAGE_HEIGHT * 11/50);

		this.add(ORDER_CONFIRM_GUIDE_PANEL.getPanel());
	}
	
	private void initYesNoSelectPanel() {
		NO_BUTTON.setBackGround(Color.GRAY);
		NO_BUTTON.setFont(new Font("arian", Font.BOLD, 35));
		YES_BUTTON.setFont(new Font("arian", Font.BOLD, 35));
		YES_NO_SELECT_PANEL.addItem(NO_BUTTON, YES_BUTTON);
		
		final int PANEL_WIDTH = MIDDLE_PANEL_WIDTH  / 2;
		Component yesNoSelectComp = YES_NO_SELECT_PANEL.getPanel();
		yesNoSelectComp.setSize(PANEL_WIDTH + 10, MIDDLE_PANEL_HEIGHT / + 10);
		yesNoSelectComp.setLocation((KioskPage.PAGE_WIDTH - PANEL_WIDTH) / 2, KioskPage.PAGE_HEIGHT * 5 / 6);
		
		this.add(yesNoSelectComp);
	}
	
	// set 설정하는 느낌
	private void setListener() {
		this.NO_BUTTON.addActionListener((args)-> {
			MainFrame.attachPanel(new OrderPage());
		}); // 한줄짜리는 { } 지울수 있음.
		
		this.YES_BUTTON.addActionListener((args) -> {
				if(mileage.top.getText().equals("")&&mileage.middle.getText().equals("")&&mileage.bottom.getText().equals("")) {
					MileagePanel.number[0] = mileage.top.getText();
					MileagePanel.number[1] = mileage.middle.getText();
					MileagePanel.number[2] = mileage.bottom.getText();
					if(ChoosePlace.sendserver() == -1)
						return;
					MainFrame.attachPanel(new ThankPage());
				}	
				else if(mileage.top.getText().equals("010") == false)
					return;
				else if(mileage.top.getText().length() == 3 && mileage.middle.getText().length() == 4 && mileage.bottom.getText().length() == 4) {
					MileagePanel.number[0] = mileage.top.getText().replace("010","8210");// 핸드폰번호 담아놓음
					MileagePanel.number[1] = mileage.middle.getText();
					MileagePanel.number[2] = mileage.bottom.getText();
					if(ChoosePlace.sendserver() == -1)
						return;
			        MainFrame.attachPanel(new ThankPage());
				}
				else
					return;
		});
	}
}

class ValueChanged implements TextListener {
	int count = 0;
	JTextField top;
	JTextField middle;
	JTextField bottom;
	
	ValueChanged(JTextField top, JTextField middle, JTextField bottom) {
		this.top = top;
		this.middle = middle;
		this.bottom = bottom;
	}

	public void textValueChanged(TextEvent e) {
		count += 1;
		if(count == 3)
			middle.requestFocus();
		if(count == 7)
			bottom.requestFocus();
	}
}