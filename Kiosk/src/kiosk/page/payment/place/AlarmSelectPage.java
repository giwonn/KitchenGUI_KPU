package kiosk.page.payment.place;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import kiosk.MainFrame;
import kiosk.page.ImageTextButton;
import kiosk.page.KioskGuidePanel;
import kiosk.page.KioskPage;
import kiosk.page.confirm.Phonenumber;
import kiosk.page.eatplace.EatPlacePage;
import kiosk.page.order.OrderPage;
import kiosk.page.order.OrderPlace;

/**
 * Class Role : 알림종류 선택 페이지
 */
public class AlarmSelectPage extends KioskPage {

	private static final int MID_PANEL_WIDTH = KioskPage.PAGE_WIDTH * 4 / 5;
	private static final int MID_PANEL_HEIGHT = KioskPage.PAGE_HEIGHT * 2 / 5;

	private final KioskGuidePanel PAYMENT_SELECT_PANEL = new KioskGuidePanel("알림 종류를 선택해주세요.", 0, 2);

	private final ImageTextButton COUNTER_BUTTON = new ImageTextButton();
	private final ImageTextButton CARD_BUTTON = new ImageTextButton();

	public AlarmSelectPage() {
		initAlarmSelectPage();
		initAlarmSelectPanel();
		setListeners();
	}
	
	private void initAlarmSelectPage() {
		this.setBackgroundImg("image/background.png");
		this.showBackgroundImg(true);
		this.showBackButton(true);
	}
	
	private void initAlarmSelectPanel() {
		initKioskButton();
		PAYMENT_SELECT_PANEL.addItem(COUNTER_BUTTON, CARD_BUTTON);
		PAYMENT_SELECT_PANEL.getPanel().setSize(MID_PANEL_WIDTH, MID_PANEL_HEIGHT);
		PAYMENT_SELECT_PANEL.getPanel().setLocation((KioskPage.PAGE_WIDTH - MID_PANEL_WIDTH) / 2, KioskPage.PAGE_HEIGHT / 4);
		
		this.add(PAYMENT_SELECT_PANEL.getPanel());
	}
	
	private void initKioskButton() {
		final int BUTTON_WIDTH = MID_PANEL_WIDTH / 3;
		final int BUTTON_HEIGHT = MID_PANEL_HEIGHT / 2;
		
		COUNTER_BUTTON.setText("어플 알림");
		COUNTER_BUTTON.setResizedImg(new ImageIcon("image/어플알림.jpg"), BUTTON_WIDTH, BUTTON_HEIGHT);

		CARD_BUTTON.setText("<html><center>" + ("진동벨 알림") + "</center></html>");
		CARD_BUTTON.setResizedImg(new ImageIcon("image/진동벨알림.jpg"), BUTTON_WIDTH, BUTTON_HEIGHT);
	}
	
	private void setListeners() {
		this.BACK_BUTTON.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.attachPanel(new EatPlacePage());
			}
		});

		COUNTER_BUTTON.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderPlace.getInstance().setAlarmSelect(AlarmSelect.APP);
				MainFrame.attachPanel(new OrderPage());
//				LoadingPage loading=new LoadingPage();
//				loading.start();
			}
		});

		CARD_BUTTON.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderPlace.getInstance().setAlarmSelect(AlarmSelect.BELL);
				MainFrame.attachPanel(new OrderPage());
//				LoadingPage loading=new LoadingPage();
//				loading.start();
			}
		});
	}
}