package kiosk.page.thank;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import kiosk.Client;
import kiosk.MainFrame;
import kiosk.page.ImageTextPanel;
import kiosk.page.KioskPage;
import kiosk.page.confirm.ChoosePlace;
import kiosk.page.confirm.ConfirmPage;
import kiosk.page.confirm.MileagePanel;
import kiosk.page.confirm.OrderData;
import kiosk.page.confirm.OrderTotalDataPanel;
import kiosk.page.confirm.Phonenumber;
import kiosk.page.eatplace.EatPlacePage;
import kiosk.page.order.CartPanel;
import kiosk.page.order.MenuButton;
import kiosk.page.order.OrderPlace;
import kiosk.page.order.SelectedMenu;
import kiosk.page.welcome.WelcomePage;
import kiosk.page.welcome.WelcomePage2;

/** 
 * Class Role : 주문 완료 안내 페이지
 */
public class ThankPage extends KioskPage {

	private final ImageTextPanel THANK_IMG_TEXT_PANEL = new ImageTextPanel(new ImageIcon("image/bg_thanks.jpg"),
			"주문이 완료되었습니다.");
	StringBuilder send = new StringBuilder();
	public static int OrderNum = 1;
	public ThankPage() {
		sendserver();
		initPage();
		initOrderData();
		Phonenumber.initPhoneNumber();		// 핸드폰번호 지우기
		initThankImgTextPanel();
		setListener();

	}

	private void initPage() {
		this.showBackgroundImg(false);
		this.showBackButton(false);
	}
	
	private void initThankImgTextPanel() {
		THANK_IMG_TEXT_PANEL.setTextBackground(new Color(00, 94, 00));
		//THANK_IMG_TEXT_PANEL.setTextBackground(Color.GREEN);
		
		Component thankImgTextPanel = THANK_IMG_TEXT_PANEL.getPanel();
		thankImgTextPanel.setSize(KioskPage.PAGE_WIDTH, KioskPage.PAGE_HEIGHT);
		thankImgTextPanel.setLocation(0, 0);
		
		this.add(thankImgTextPanel);
	}

	private void initOrderData() {
		OrderPlace.getInstance().init();
		SelectedMenu.init();
		CartPanel.initCart();
		initgetMenu();
		MenuButton.info.clear();
	}

	private void setListener() {

			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					MainFrame.attachPanel(new WelcomePage2());
				}
			});
	}
	private StringBuilder getMenu() {
		int binary;
		int amount;
		int size;
		int temp;
		for(int i=0;i<MenuButton.info.size();i++) {
			binary = MenuButton.info.get(i).getBinary();
			amount = MenuButton.info.get(i).getAmount();
			size = MenuButton.info.get(i).sendSize();
			temp = MenuButton.info.get(i).sendTemp();
			send.append(".").append(binary).append(".").append(amount).append(".").append(size).append(".").append(temp);
		}
		return send;
	}
	private void initgetMenu() {
		send = null;
	}
	
	private void sendserver() {
		// 무인주문,매장식사,어플알림,메뉴종류개수,주문번호,핸드폰번호,메뉴목록(+개수)
			try {
//				if(OrderPlace.getAlarmSelect().equals("BELL")) {
					Client.bw.write("0000" + "," + "0" + "," +			// 무인주문대, 무인주문or어플예약
									ChoosePlace.sendserver() + "," +		// 식사 or테이크아웃(포장)
									"1" + ","+	// 알림(어플or진동벨)
									Integer.toBinaryString(OrderTotalDataPanel.menunum()) + "," + // 메뉴종류개수
									OrderNum + "." +		// 주문번호
									MileagePanel.getnumber() +	//핸드폰번호
									getMenu() + "\n"); // 주문메뉴(이름,갯수)
					Client.bw.flush();
//				}
/*				else {
					Client.bw.write("0000" + "," + "0" + "," +
									EatPlacePage.sendserver() + "," +		// 식사 or테이크아웃(포장)
									OrderPlace.sendserver() + ","+	// 알림(어플or진동벨)
									Integer.toBinaryString(OrderTotalDataPanel.menunum()) + "," + // 메뉴종류개수
									OrderNum + "." +
									Phonenumber.getnumber() +	//핸드폰번호
									getMenu() + "\n");
					Client.bw.flush();
				}*/
			} catch (IOException e) {e.printStackTrace();}
	}
}